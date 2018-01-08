package ru.rodionov.elegionweather.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import ru.rodionov.elegionweather.api.ResponseWeather;
import ru.rodionov.elegionweather.api.WeatherFactory;
import ru.rodionov.elegionweather.objects.FindResult;

/**
 * Created by rodionov on 08.08.2016.
 */
public class CityFindLoader extends AsyncTaskLoader<ResponseWeather> {

    public static final String CITY = "city";
    private String mCityName;

    public CityFindLoader(Context context, Bundle bndl){
        super(context);

        if(bndl != null)
            this.mCityName = bndl.getString(CITY);
    }

    @Override
    public ResponseWeather loadInBackground() {

        ResponseWeather respWeather = new ResponseWeather();
        Call<FindResult> callWeather = WeatherFactory.getWeatherAPI().searchCityByName(mCityName);
        try {

            Response<FindResult> response = callWeather.execute();
            respWeather.setListWeather(response.body().getList());
            respWeather.setRequestResult(ResponseWeather.SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return respWeather;
    }
}
