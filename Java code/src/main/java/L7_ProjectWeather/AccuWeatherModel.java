package L7_ProjectWeather;

import L7_ProjectWeather.entity.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccuWeatherModel implements WeatherModel {
    private static final String PROTOKOL = "http";
    private static final String API_KEY = "cy1D00ZlQ30ALq2ni5ff4lED9GGkejiA";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_V1 = "v1";
    private static final String LOCATIONS_ENDPOINT = "locations";
    private static final String CITIES_ENDPOINT = "cities";
    private static final String AUTOCOMPLETE_ENDPOINT = "autocomplete";

    /**
     * New Endpoints
     */
    public static final String FORECAST_ENDPOINT = "forecasts";
    public static final String DAILY_ENDPOINT = "daily";
    public static final String FIVE_DAYS_ENDPOINT = "5day";


    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Weather weather;
    private Weather weatherForFiveDays;


    public Weather jsonParser(String response, Period period, String city) throws IOException, ParseException {
        if (period == Period.NOW) {

            JsonNode localDateNode = objectMapper
                    .readTree(response).get(0)
                    .at("/LocalObservationDateTime");
            String parsedDate = localDateNode.asText();

            JsonNode temperatureNode = objectMapper
                    .readTree(response).get(0)
                    .at("/Temperature/Metric/Value");
            String parsedTemp = temperatureNode.asText();


            JsonNode weatherTextNode = objectMapper
                    .readTree(response).get(0)
                    .at("/WeatherText");
            String parsedWeatherText = weatherTextNode.asText();

            double parsedTempInC = (Double.parseDouble(parsedTemp) - 32) / 1.8; // Перевод Фарингейт в Цельсий

            weather = new Weather(city, parsedDate, parsedTempInC, parsedWeatherText);

            return weather;
        }

        if (period == Period.FIVE_DAYS) {

            List<String> datesArrayList = new ArrayList<>();
            List<Double> tempArrayList = new ArrayList<>();

            JsonNode jsonNode = objectMapper.readTree(response).at("/DailyForecasts");
            for (JsonNode node : jsonNode) {
                JsonNode dateNode = objectMapper.readTree(response).at("/Date");
                JsonNode tempMinNode = objectMapper.readTree(String.valueOf(node)).at("/Temperature/Minimum/Value");
                JsonNode tempMaxNode = objectMapper.readTree(String.valueOf(node)).at("/Temperature/Maximum/Value");

                datesArrayList.add(dateNode.asText()); // Добавляем в массив дат

                double avgTempInC = ((Double.parseDouble(tempMinNode.asText())) +
                        (Double.parseDouble(tempMaxNode.asText())) - 32) / 1.8; // Перевод Фарингейт в Цельсий
                tempArrayList.add(avgTempInC); // Добавляем в массив температур
            }
            JsonNode headlineNode = objectMapper.readTree(response).at("/Headline");

            JsonNode textNode = objectMapper.readTree(String.valueOf(headlineNode)).at("/Text"); // Получаем текстовое поле для раздела WeatherText
            weatherForFiveDays = new Weather(city, textNode.asText(), datesArrayList, tempArrayList); // По второму конструктору создаем экземпляр Weather с массивами

        }
        return null;
    }

    public void getWeather(Period period, String selectedCity) throws IOException {
        String cityKey = detectCityKey(selectedCity);
        if (period == Period.NOW) {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOKOL)
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    .addPathSegment(API_V1)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(httpUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            String responseString = response.body().string();
            System.out.println(responseString);


            //TODO: вызвать метод сохранения погоды в базу из DataBaseRepository, предварительно из responseString // ++++++++++++++++++
            //достав нужные данные для создания объекта Weather

            /** Выводим погоду в читабельном виде */

            try {
                weather = jsonParser(responseString, Period.NOW, selectedCity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Today, " + weather.getLocalDate() + " in " + weather.getCity() + " the weather is " + weather.getWeatherText() + ". And temperature is " + weather.getTemperature() + " degrees Celsius");
            System.out.println();

            DataBaseRepository dataBaseRepository = new DataBaseRepository();
            try {
                dataBaseRepository.saveWeatherData(jsonParser(responseString, Period.NOW, selectedCity)); // Здесь вызываем метод для
                // парсинга одного дня. Выдаст ошибку по SQL, но она обработана. К сожалению с SQLite пока не разобрался
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }

        }

        if (period == Period.FIVE_DAYS) {
            //TODO: Домашнее задание со звездочкой // ++++++++++++++++++
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOKOL)
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_V1)
                    .addPathSegment(DAILY_ENDPOINT)
                    .addPathSegment(FIVE_DAYS_ENDPOINT)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request requestFiveDays = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(httpUrl)
                    .build();

            Response responseFiveDays = okHttpClient.newCall(requestFiveDays).execute();
            String responseStringFiveDays = responseFiveDays.body().string();
            System.out.println(responseStringFiveDays);


            try {
                jsonParser(responseStringFiveDays, Period.FIVE_DAYS, selectedCity);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DataBaseRepository dataBaseRepository = new DataBaseRepository();
            try {
                dataBaseRepository.saveWeatherData(jsonParser(responseStringFiveDays, Period.FIVE_DAYS, selectedCity)); // Здесь вызываем метод для
                // парсинга недели. Выдаст ошибку по SQL, но она обработана. К сожалению с SQLite пока не разобрался
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }


        }
    }

    @Override
    public void getSavedWeatherData() {
        //TODO: Обратиться к  DataBaseRepository и вызвать метод getSavedWeatherData //-----------------


    }

    public String detectCityKey(String selectedCity) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS_ENDPOINT)
                .addPathSegment(API_V1)
                .addPathSegment(CITIES_ENDPOINT)
                .addPathSegment(AUTOCOMPLETE_ENDPOINT)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();

        return cityKey;
    }
}
