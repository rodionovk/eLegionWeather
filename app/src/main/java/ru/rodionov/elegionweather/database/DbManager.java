package ru.rodionov.elegionweather.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.ArrayList;

import ru.rodionov.elegionweather.objects.CityWeather;
import ru.rodionov.elegionweather.objects.ListWeatherCity;

/**
 * Created by rodionov on 08.08.2016.
 */
public class DbManager {

    private WeatherDbHelper mSQLHelper;

    public DbManager(Context context){
        this.mSQLHelper = new WeatherDbHelper(context);
    }

    public String getAllCityID(){

        SQLiteDatabase database =  mSQLHelper.getWritableDatabase();
        Cursor cursor = database.query(WeatherDatabase.CityWeatherEntry.TABLE_NAME, null, null, null, null, null, null);

        String allCityID = "";
        if (cursor.moveToFirst()) {
            do {
                int cityID = cursor.getInt(cursor.getColumnIndex(WeatherDatabase.CityWeatherEntry.COLUMN_CITY_ID));

                if(!cursor.isLast())
                    allCityID += cityID + ",";
                else
                    allCityID += cityID;

            } while (cursor.moveToNext());
        }
        cursor.close();
        return allCityID;
    }

    public long insertCityWeather(CityWeather cityweather){

        SQLiteDatabase database =  mSQLHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(WeatherDatabase.CityWeatherEntry.COLUMN_CITY_ID, cityweather.getId());
        cv.put(WeatherDatabase.CityWeatherEntry.COLUMN_INFO, "");

        return database.insert(WeatherDatabase.CityWeatherEntry.TABLE_NAME,null,cv);
    }

    public int deleteCityWeather(int cityid){

        SQLiteDatabase database =  mSQLHelper.getWritableDatabase();

        String[] wherenArgs = new String[] {String.valueOf(cityid) };
        return database.delete(WeatherDatabase.CityWeatherEntry.TABLE_NAME,WeatherDatabase.CityWeatherEntry.COLUMN_CITY_ID + " = ?", wherenArgs);
    }

    public int updateCityWeather(CityWeather cityweather){

        SQLiteDatabase database =  mSQLHelper.getWritableDatabase();

        String[] whereArgs = new String[] { String.valueOf(cityweather.getId()) };
        Gson gson = new Gson();
        String json = gson.toJson(cityweather);

        ContentValues cv = new ContentValues();
        cv.put(WeatherDatabase.CityWeatherEntry.COLUMN_INFO,json);
        return database.update(WeatherDatabase.CityWeatherEntry.TABLE_NAME, cv, WeatherDatabase.CityWeatherEntry.COLUMN_CITY_ID + " = ?", whereArgs);
    }

    public ArrayList<CityWeather> getListCityWeather(){

        SQLiteDatabase database =  mSQLHelper.getWritableDatabase();
        Cursor cursor = database.query(WeatherDatabase.CityWeatherEntry.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<CityWeather> listWeather = new ArrayList<CityWeather>();

        if (cursor.moveToFirst()) {
            do {

                String weatherInfo = cursor.getString(cursor.getColumnIndex(WeatherDatabase.CityWeatherEntry.COLUMN_INFO));

                if(weatherInfo.length() > 0){

                    Gson gson = new Gson();
                    CityWeather obj = gson.fromJson(weatherInfo,CityWeather.class);
                    listWeather.add(obj);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();

        return listWeather;
    }

    public void save(ArrayList<CityWeather> weathers){

        for(int i=0;i<weathers.size();i++){

            CityWeather cw = weathers.get(i);
            updateCityWeather(cw);
        }
    }

}
