package L7_L8_ProjectWeather;

import L7_L8_ProjectWeather.entity.Weather;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DataBaseRepository {
    private static final String DB_NAME = "geekbrains.db";
    String insertWeather = "insert into weather (city, localdate, temperature, weathertext) values (?, ?, ?, ?)";
    String getWeather = "select * from weather";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //create table weather (id integer primary key autoincrement, city text, localdate text, temperature real)
    public boolean saveWeatherData(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db")) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocalDate());
            saveWeather.setDouble(3, weather.getTemperature());
            saveWeather.setString(4, weather.getWeatherText());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }

////    TODO: Реализовать метод для считывания данных о погоде //-----------------
    public List<Weather> getSavedWeatherData(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db")) {
            Statement statement = connection.createStatement();
//            Weather weatherFromDB = new Weather();
            ResultSet resultWeatherSet = statement.executeQuery(getWeather);
            while (resultWeatherSet.next()) {
                weather.setCity(resultWeatherSet.getString("city"));
                weather.setLocalDate(resultWeatherSet.getString("localdate"));
                weather.setTemperature(resultWeatherSet.getDouble("temperature"));
                weather.setWeatherText(resultWeatherSet.getString("weathertext"));
            }
//            System.out.println(weather.getCity());
//            System.out.println(weather.getLocalDate());
//            System.out.println(weather.getTemperature());
//            System.out.println(weather.getWeatherText());


            return Arrays.asList(weather);
        }
    }

    public static void main(String[] args) throws SQLException {
        DataBaseRepository dataBaseRepository = new DataBaseRepository();
        dataBaseRepository.saveWeatherData(
                new Weather("Moscow", "12.12.12", 0.3, "Хорошая"));
    }
}
