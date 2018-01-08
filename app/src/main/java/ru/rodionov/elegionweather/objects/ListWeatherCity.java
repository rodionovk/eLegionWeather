
package ru.rodionov.elegionweather.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ListWeatherCity {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<CityWeather> list = new ArrayList<CityWeather>();

    /**
     * 
     * @return
     *     The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * 
     * @param cnt
     *     The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * 
     * @return
     *     The list
     */
    public ArrayList<CityWeather> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(ArrayList<CityWeather> list) {
        this.list = list;
    }

}
