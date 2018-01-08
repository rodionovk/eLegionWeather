package ru.rodionov.elegionweather.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodionov on 08.08.2016.
 */
public class WeatherFactory {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

    private static final String APP_ID = "dfd821de1781747ab6ebb3e8e1d13984";
    private static final String UNITS = "metric";
    private static final String LANG = "ru";

    public static final String PARAM_REQUEST = "APPID=" + WeatherFactory.APP_ID + "&units=" + WeatherFactory.UNITS + "&lang=" + WeatherFactory.LANG;

    public static WeatherAPI getWeatherAPI() {
        return getRetrofit().create(WeatherAPI.class);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
