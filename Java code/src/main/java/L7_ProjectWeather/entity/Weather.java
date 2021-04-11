package L7_ProjectWeather.entity;

import java.util.List;

public class Weather {
    //create table weather (id integer primary key autoincrement, city text, localdate text, temperature real, weather_text)
    private String city;
    private String localDate;
    private double temperature;
    private String weatherText;

    private List<String> localDates;
    private List<Double> localTemps;

    public Weather(String city, String weatherText, List<String> localDates, List<Double> localTemps) {
        this.city = city;
        this.weatherText = weatherText;
        this.localDates = localDates;
        this.localTemps = localTemps;
    }

    public Weather(String city, String localDate, double temperature, String weatherText) {
        this.city = city;
        this.localDate = localDate;
        this.temperature = temperature;
        this.weatherText = weatherText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }
}
