package L7_ProjectWeather;

import java.io.IOException;

public class Controller {
    WeatherModel weatherModel = new AccuWeatherModel();

    // 1 - узнать текущую погоду    2 - узнать прогноз на 5 дней  выход - выход из программы
    public void getWeather(String command, String selectedCity) throws IOException {
        switch (Functionality.fromValue(command)) {
            case GET_CURRENT_WEATHER:
                weatherModel.getWeather(Period.NOW, selectedCity);
                break;
            case GET_WEATHER_IN_NEXT_FIVE_DAYS:
                weatherModel.getWeather(Period.FIVE_DAYS, selectedCity);
                //TODO: Добавить 3 опцию из Functionality(enum) // -------------------
            case QUIT:
                System.out.println("Программа завершила работу! Спасибо за то, что пользовались ORBS Technology =)");
                System.exit(0);
            default:
                System.out.println("Неверный ввод! Попробуйте еще раз");
        }
    }
}
