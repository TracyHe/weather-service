package com.oreilly.weather;

import com.oreilly.weather.model.Weather;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OpenWeatherMap {

    @GET("/data/2.5/weather")
    Weather getWeather(@Query("q") String city, @Query("appId") String appId);
}
