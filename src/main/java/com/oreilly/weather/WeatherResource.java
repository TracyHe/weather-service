package com.oreilly.weather;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.oreilly.weather.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.annotation.CacheResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class WeatherResource {

    private static Logger log = LoggerFactory.getLogger(WeatherResource.class);

    @Inject
    private OpenWeatherMap openWeatherMap;

    @Inject
    @Named("appId")
    private String appId;

    @GET
    @Path("/weather/{city}")
    @Produces("application/json")
    @CacheResult(cacheName = "weather")
    public WeatherResponse getWeather(@PathParam("city") String city) {
        log.info("Retrieving local weather for {}", city);

        return WeatherResponse
                .fromWeather(openWeatherMap.getWeather(city, appId));
    }
}
