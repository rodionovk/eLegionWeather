package ru.rodionov.elegionweather.api;

import java.util.ArrayList;

import ru.rodionov.elegionweather.objects.CityWeather;

/**
 * Created by rodionov on 08.08.2016.
 */
public class ResponseWeather {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int mRequestResult;
    private ArrayList<CityWeather> mListWeather;

    public ResponseWeather() {
        mRequestResult = ERROR;
    }

    public void setListWeather(ArrayList<CityWeather> list){
        this.mListWeather = list;
    }

    public ArrayList<CityWeather> getListWeather(){
        return mListWeather;
    }

    public void setRequestResult(int result){
        this.mRequestResult = result;
    }

    public int getRequestResult(){
        return  this.mRequestResult;
    }
}
