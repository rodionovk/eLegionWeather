package ru.rodionov.elegionweather.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.rodionov.elegionweather.objects.FindResult;
import ru.rodionov.elegionweather.objects.ListWeatherCity;

/**
 * Created by rodionov on 08.08.2016.
 */
public interface WeatherAPI {

    @GET("group?" + WeatherFactory.PARAM_REQUEST)
    Call<ListWeatherCity> getCurrentWeatherByGroupCityID(@Query("id") String cityid);

    @GET("find?" + WeatherFactory.PARAM_REQUEST)
    Call<FindResult> searchCityByName(@Query("q") String cityname);
}
