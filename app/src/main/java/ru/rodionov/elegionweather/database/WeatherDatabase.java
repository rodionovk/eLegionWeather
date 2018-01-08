package ru.rodionov.elegionweather.database;

import android.provider.BaseColumns;

/**
 * Created by rodionov on 08.08.2016.
 */
public final class WeatherDatabase {

    public static abstract class CityWeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "tblCityWeather";
        public static final String COLUMN_CITY_ID = "cityid";
        public static final String COLUMN_INFO = "weatherinfo";
    }

    public static final String SQL_CREATE_TABLE_CITYWEATHER = "CREATE TABLE IF NOT EXISTS " + CityWeatherEntry.TABLE_NAME + " (" +
            CityWeatherEntry._ID + " INTEGER PRIMARY KEY," +
            CityWeatherEntry.COLUMN_CITY_ID + " INTEGER, " +
            CityWeatherEntry.COLUMN_INFO + " TEXT);";

    public static final String SQL_DELETE_TABLE_CITYWEATHER =
            "DROP TABLE IF EXISTS " + CityWeatherEntry.TABLE_NAME;

    public static final String insertCity(int cityId){
        return "INSERT INTO " + CityWeatherEntry.TABLE_NAME + " (" +
                CityWeatherEntry.COLUMN_CITY_ID + ", "  + CityWeatherEntry.COLUMN_INFO + ") " +
                "VALUES (" + cityId + ", '')";
    }

}
