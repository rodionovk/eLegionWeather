
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ru.rodionov.elegionweather.api.WeatherFactory;


public class CityWeather implements Parcelable{

    @SerializedName("coord")
    @Expose
    private Coord mCoord;
    @SerializedName("weather")
    @Expose
    private List<Weather> mWeather = new ArrayList<Weather>();
    @SerializedName("base")
    @Expose
    private String mBase;
    @SerializedName("main")
    @Expose
    private Main mMain;
    @SerializedName("wind")
    @Expose
    private Wind mWind;
    @SerializedName("rain")
    @Expose
    private Rain mRain;
    @SerializedName("snow")
    @Expose
    private Snow mSnow;
    @SerializedName("clouds")
    @Expose
    private Clouds mClouds;
    @SerializedName("dt")
    @Expose
    private Integer mDt;
    @SerializedName("sys")
    @Expose
    private Sys mSys;
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("cod")
    @Expose
    private Integer mCod;

    public String getUrlPic(){
        return WeatherFactory.ICON_URL + mWeather.get(0).getIcon() + ".png";
    }

    /**
     * 
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return mCoord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.mCoord = coord;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public List<Weather> getWeather() {
        return mWeather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(List<Weather> weather) {
        this.mWeather = weather;
    }

    /**
     * 
     * @return
     *     The base
     */
    public String getBase() {
        return mBase;
    }

    /**
     * 
     * @param base
     *     The base
     */
    public void setBase(String base) {
        this.mBase = base;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return mMain;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.mMain = main;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return mWind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.mWind = wind;
    }

    /**
     * 
     * @return
     *     The rain
     */
    public Rain getRain() {
        return mRain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    public void setRain(Rain rain) {
        this.mRain = rain;
    }

    /**
     *
     * @return
     *     The snow
     */
    public Snow getSnow() {
        return mSnow;
    }

    /**
     *
     * @param snow
     *     The snow
     */
    public void setSnow(Snow snow) {
        this.mSnow = snow;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public Clouds getClouds() {
        return mClouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(Clouds clouds) {
        this.mClouds = clouds;
    }

    /**
     * 
     * @return
     *     The dt
     */
    public Integer getDt() {
        return mDt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.mDt = dt;
    }

    /**
     * 
     * @return
     *     The sys
     */
    public Sys getSys() {
        return mSys;
    }

    /**
     * 
     * @param sys
     *     The sys
     */
    public void setSys(Sys sys) {
        this.mSys = sys;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return mId;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.mId = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return mName;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * 
     * @return
     *     The cod
     */
    public Integer getCod() {
        return mCod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(Integer cod) {
        this.mCod = cod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();

        parcel.writeParcelable(mCoord,i);
        parcel.writeTypedList(mWeather );
        parcel.writeString(mBase);
        parcel.writeParcelable(mMain,i);
        parcel.writeParcelable(mWind,i);
        parcel.writeParcelable(mRain,i);
        parcel.writeParcelable(mSnow,i);
        parcel.writeParcelable(mClouds,i);
        parcel.writeInt(mDt);
        parcel.writeParcelable(mSys,i);
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeInt(mCod);
    }

    private void initFieldsIfNull(){

        if(mBase == null)
            mBase = "";
        if(mDt == null)
            mDt = 0;
        if(mId == null)
            mId = 0;
        if(mName == null)
            mName = "";
        if(mCod == null)
            mCod = 0;
    }

    public static final Creator<CityWeather> CREATOR = new Creator<CityWeather>() {

        public CityWeather createFromParcel(Parcel in) {
            return new CityWeather(in);
        }

        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };

    private CityWeather(Parcel parcel) {
        mCoord = (Coord)parcel.readParcelable(Coord.class.getClassLoader());
        if (mWeather  == null) {
            mWeather = new ArrayList<Weather>();
        }
        parcel.readTypedList(mWeather, Weather.CREATOR);
        mBase = parcel.readString();
        mMain = (Main)parcel.readParcelable(Main.class.getClassLoader());
        mWind = (Wind) parcel.readParcelable(Wind.class.getClassLoader());
        mRain = (Rain) parcel.readParcelable(Rain.class.getClassLoader());
        mSnow = (Snow) parcel.readParcelable(Snow.class.getClassLoader());
        mClouds = (Clouds) parcel.readParcelable(Clouds.class.getClassLoader());
        mDt = parcel.readInt();
        mSys = (Sys) parcel.readParcelable(Sys.class.getClassLoader());
        mId = parcel.readInt();
        mName = parcel.readString();
        mCod = parcel.readInt();
    }
}
