package com.oreilly.weather;

import com.google.inject.Module;
import com.oreilly.microservices.Microservice;
import org.jsr107.ri.annotations.guice.module.CacheAnnotationsModule;

public class WeatherService extends Microservice {

    public static void main(String... args) {
        new WeatherService().run();
    }

    @Override
    public Module[] getModules() {
        return new Module[] {
                new CacheAnnotationsModule(),
                new WeatherModule()
        };
    }
}
