package ru.rodionov.elegionweather.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import ru.rodionov.elegionweather.api.ResponseWeather;
import ru.rodionov.elegionweather.api.WeatherFactory;
import ru.rodionov.elegionweather.database.DbManager;
import ru.rodionov.elegionweather.objects.CityWeather;
import ru.rodionov.elegionweather.objects.ListWeatherCity;

/**
 * Created by rodionov on 08.08.2016.
 */
public class WeatherLoader extends AsyncTaskLoader<ResponseWeather> {

    private Context mContext;
    private ResponseWeather mResponse;

    public WeatherLoader(Context context){
        super(context);

        this.mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(mResponse != null){
            deliverResult(mResponse);
        }

        if(takeContentChanged() || mResponse == null) {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mResponse = null;
    }

    @Override
    public ResponseWeather loadInBackground() {

        ResponseWeather respWeather = new ResponseWeather();

        DbManager dbmanager = new DbManager(mContext);
        String allCityID = dbmanager.getAllCityID();

        Call<ListWeatherCity> myCall = WeatherFactory.getWeatherAPI().getCurrentWeatherByGroupCityID(allCityID);
        ArrayList<CityWeather> listWeather;
        try {

            Response<ListWeatherCity> response = myCall.execute();
            listWeather = response.body().getList();
            respWeather.setRequestResult(ResponseWeather.SUCCESS);
            dbmanager.save(listWeather);

        } catch (IOException e) {
            listWeather = dbmanager.getListCityWeather();
        }
        respWeather.setListWeather(listWeather);

        mResponse = respWeather;
        return mResponse;
    }
}
