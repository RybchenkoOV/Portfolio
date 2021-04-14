package L7_L8_ProjectWeather;

import java.io.IOException;
import java.sql.SQLException;

public interface WeatherModel {
    void getWeather(Period period, String selectedCity) throws IOException;

    void getSavedWeatherData() throws SQLException;
}
