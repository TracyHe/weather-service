package com.oreilly.weather;

import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.oreilly.weather.model.WeatherResponse;
import com.typesafe.config.Config;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

public class WeatherModule extends RequestScopeModule {

    static {
        MutableConfiguration<String, WeatherResponse> config =
                new MutableConfiguration<>();
        config.setExpiryPolicyFactory(AccessedExpiryPolicy
                .factoryOf(new Duration(TimeUnit.MINUTES, 5)));
        Caching.getCachingProvider().getCacheManager()
                .createCache("weather", config);
    }

    public void configure() {
        bind(WeatherResource.class).in(Scopes.SINGLETON);
    }

    @Provides
    public OpenWeatherMap openWeatherMap(Config config) {
        return new RestAdapter.Builder()
                .setEndpoint(config.getString("openWeatherMap.url"))
                .setConverter(new JacksonConverter())
                .build()
                .create(OpenWeatherMap.class);
    }

    @Provides
    @Named("appId")
    public String appId(Config config) {
        return config.getString("openWeatherMap.appId");
    }
}
