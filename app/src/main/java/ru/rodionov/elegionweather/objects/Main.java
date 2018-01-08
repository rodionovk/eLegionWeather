
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main implements Parcelable{

    @SerializedName("temp")
    @Expose
    private Double mTemp;
    @SerializedName("pressure")
    @Expose
    private Double mPressure;
    @SerializedName("humidity")
    @Expose
    private Integer mHumidity;
    @SerializedName("temp_min")
    @Expose
    private Double mTempMin;
    @SerializedName("temp_max")
    @Expose
    private Double mTempMax;
    @SerializedName("sea_level")
    @Expose
    private Double mSeaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Double mGrndLevel;

    /**
     * 
     * @return
     *     The temp
     */
    public Double getTemp() {
        return mTemp;
    }

    public int getRoundTemp(){
        return (int)Math.round(mTemp);
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Double temp) {
        this.mTemp = temp;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public Double getPressure() {
        return mPressure;
    }

    public int getRoundPressure(){
        return (int)Math.round(mPressure * 0.75006375541921);
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(Double pressure) {
        this.mPressure = pressure;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Integer getHumidity() {
        return mHumidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Integer humidity) {
        this.mHumidity = humidity;
    }

    /**
     * 
     * @return
     *     The tempMin
     */
    public Double getTempMin() {
        return mTempMin;
    }

    /**
     * 
     * @param tempMin
     *     The temp_min
     */
    public void setTempMin(Double tempMin) {
        this.mTempMin = tempMin;
    }

    /**
     * 
     * @return
     *     The tempMax
     */
    public Double getTempMax() {
        return mTempMax;
    }

    /**
     * 
     * @param tempMax
     *     The temp_max
     */
    public void setTempMax(Double tempMax) {
        this.mTempMax = tempMax;
    }

    /**
     * 
     * @return
     *     The seaLevel
     */
    public Double getSeaLevel() {
        return mSeaLevel;
    }

    /**
     * 
     * @param seaLevel
     *     The sea_level
     */
    public void setSeaLevel(Double seaLevel) {
        this.mSeaLevel = seaLevel;
    }

    /**
     * 
     * @return
     *     The grndLevel
     */
    public Double getGrndLevel() {
        return mGrndLevel;
    }

    /**
     * 
     * @param grndLevel
     *     The grnd_level
     */
    public void setGrndLevel(Double grndLevel) {
        this.mGrndLevel = grndLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();

        parcel.writeDouble(mTemp);
        parcel.writeDouble(mPressure);
        parcel.writeInt(mHumidity);
        parcel.writeDouble(mTempMin);
        parcel.writeDouble(mTempMax);
        parcel.writeDouble(mSeaLevel);
        parcel.writeDouble(mGrndLevel);
    }

    private void initFieldsIfNull(){

        if(mTemp == null)
            mTemp = 0d;
        if(mPressure == null)
            mPressure = 0d;
        if(mHumidity == null)
            mHumidity = 0;
        if(mTempMin == null)
            mTempMin = 0d;
        if(mTempMax == null)
            mTempMax = 0d;
        if(mSeaLevel == null)
            mSeaLevel = 0d;
        if(mGrndLevel == null)
            mGrndLevel = 0d;
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {

        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    private Main(Parcel parcel) {
        mTemp = parcel.readDouble();
        mPressure = parcel.readDouble();
        mHumidity = parcel.readInt();
        mTempMin = parcel.readDouble();
        mTempMax = parcel.readDouble();
        mSeaLevel = parcel.readDouble();
        mGrndLevel = parcel.readDouble();
    }
}
