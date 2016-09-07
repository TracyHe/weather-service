package com.oreilly.weather.model;

import com.oreilly.weather.util.Kelvin;

import java.io.Serializable;

public class WeatherResponse implements Serializable {

    private String conditions;
    private String description;
    private String temperature;
    private String pressure;
    private String humidity;
    private String city;

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static WeatherResponse fromWeather(Weather weather) {
        WeatherResponse response = new WeatherResponse();
        Weather.Condition conditions = weather.getWeather().iterator().next();

        response.setCity(weather.getName());
        response.setConditions(conditions.getMain());
        response.setDescription(conditions.getDescription());
        response.setPressure(weather.getMain().getPressure() + "mb");
        response.setTemperature(Kelvin.toFahrenheit(weather.getMain().getTemp()) + "\u00b0F");
        response.setHumidity(weather.getMain().getHumidity() + "%");

        return response;
    }
}
