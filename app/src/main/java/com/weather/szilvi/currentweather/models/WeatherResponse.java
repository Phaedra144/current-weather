package com.weather.szilvi.currentweather.models;

public class WeatherResponse {

    Weather weather;
    Main main;

    public Weather getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weather=" + weather +
                ", main=" + main +
                '}';
    }
}
