package ru.rodionov.elegionweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodionov on 08.08.2016.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cityweather.db";
    public static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(WeatherDatabase.SQL_CREATE_TABLE_CITYWEATHER);

        List<Integer> listCity = new ArrayList<>();
        listCity.add(551487); /*Казань*/
        listCity.add(524901); /*Москва*/
        listCity.add(498817); /*Санкт-Петербург*/
        listCity.add(2643743); /*Лондон*/
        listCity.add(5128638); /*Нью Йорк*/
        listCity.add(1850147); /*Токио*/
        listCity.add(2950159); /*Берлин*/

        for(int i=0; i<listCity.size();i++){
            sqLiteDatabase.execSQL(WeatherDatabase.insertCity(listCity.get(i)));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(WeatherDatabase.SQL_DELETE_TABLE_CITYWEATHER);
        onCreate(sqLiteDatabase);
    }

}
