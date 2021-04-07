package L6_Network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecast {
    /**
     * General API info
     */
    public static final String API_KEY = "9Kpt86GYrEGyiRnrt7XQZHHEX5jlJvUz";
    /**
     * General URL constructor
     */
    public static final String PROTOCOL = "http";
    public static final String HOST = "dataservice.accuweather.com";
    public static final String API_V1 = "v1";
    /**
     * Endpoints
     */
    public static final String FORECAST_ENDPOINT = "forecasts";
    public static final String DAILY_ENDPOINT = "daily";
    public static final String FIVE_DAYS_ENDPOINT = "5day";
    public static final String LOCATIONS_ENDPOINT = "locations";
    public static final String CITIES_ENDPOINT = "cities";
    public static final String AUTOCOMPLETE_ENDPOINT = "autocomplete";
    /**
     * API send and receive
     */
    public static final OkHttpClient okHttpClient = new OkHttpClient();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String CITY_TO_SEARCH = "Moscow"; // В этой версии кода просто зададим город по-умолчанию

    /**
     * Methods
     */
    public static String getForecast(String locationKey) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .addPathSegment(FORECAST_ENDPOINT)
                .addPathSegment(API_V1)
                .addPathSegment(DAILY_ENDPOINT)
                .addPathSegment(FIVE_DAYS_ENDPOINT)
                .addPathSegment(locationKey)
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

    public static String getLocationKey(String city) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .addPathSegment(LOCATIONS_ENDPOINT)
                .addPathSegment(API_V1)
                .addPathSegment(CITIES_ENDPOINT)
                .addPathSegment(AUTOCOMPLETE_ENDPOINT)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        System.out.println(objectMapper.readTree(response.body().string()));
        String locationKey = objectMapper.readTree(response.body().string()).get(0).at("/Key").asText();

        return locationKey;
    }

    public static List forecastParser(String unparsedForecast) throws JsonProcessingException {
        List<String> parsedForecast = new ArrayList<>();
        /** Parsing the header */
        String parsedHeader = objectMapper.readTree(unparsedForecast).get("Headline").at("/Text").asText();
        parsedForecast.add(String.format("The weather forecast in %s for 5 days", CITY_TO_SEARCH));
        parsedForecast.add("During the period: " + parsedHeader);
        /** Parsing the daily weather */
        String dailyForecasts = objectMapper.readTree(unparsedForecast).get("DailyForecasts").asText();
        for (int i = 0; i < dailyForecasts.length(); i++) {
            parsedForecast.add("Weather for : " + LocalDateTime.now().toString());
            String minTemp = objectMapper.readTree(unparsedForecast).get("DailyForecasts").at("/Temperature/Minimum/Value").asText();
            String maxTemp = objectMapper.readTree(unparsedForecast).get("DailyForecasts").at("/Temperature/Maximum/Value").asText();
            parsedForecast.add("Minimum temperature is: " + minTemp + "F");
            parsedForecast.add("Maximum temperature is: " + maxTemp + "F");
        }

        return parsedForecast;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Bode of forecast response");
            System.out.println(getForecast(getLocationKey(CITY_TO_SEARCH)));
            System.out.println();
            System.out.println();

            /** Beautifying the response body */
            List<String> parsedWeather = new ArrayList<>();
            parsedWeather = forecastParser(getForecast(getLocationKey(CITY_TO_SEARCH)));
            for (String line : parsedWeather) {
                System.out.println(line);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
