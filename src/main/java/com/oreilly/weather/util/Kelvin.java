package com.oreilly.weather.util;

public class Kelvin {

    public static final double KELVIN_CELSIUS_DIFF = 273.15d;

    public static double toFahrenheit(double temperature) {
        return Math.round(temperature - KELVIN_CELSIUS_DIFF) * 1.8d + 32.00d;
    }
}
