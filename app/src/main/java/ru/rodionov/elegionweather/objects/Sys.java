
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys implements Parcelable{

    @SerializedName("type")
    @Expose
    private Integer mType;
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("message")
    @Expose
    private Double mMessage;
    @SerializedName("country")
    @Expose
    private String mCountry;
    @SerializedName("sunrise")
    @Expose
    private Integer mSunrise;
    @SerializedName("sunset")
    @Expose
    private Integer mSunset;

    /**
     * 
     * @return
     *     The type
     */
    public Integer getType() {
        return mType;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Integer type) {
        this.mType = type;
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
     *     The message
     */
    public Double getMessage() {
        return mMessage;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(Double message) {
        this.mMessage = message;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return mCountry;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.mCountry = country;
    }

    /**
     * 
     * @return
     *     The sunrise
     */
    public Integer getSunrise() {
        return mSunrise;
    }

    /**
     * 
     * @param sunrise
     *     The sunrise
     */
    public void setSunrise(Integer sunrise) {
        this.mSunrise = sunrise;
    }

    /**
     * 
     * @return
     *     The sunset
     */
    public Integer getSunset() {
        return mSunset;
    }

    /**
     * 
     * @param sunset
     *     The sunset
     */
    public void setSunset(Integer sunset) {
        this.mSunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();
        parcel.writeInt(mType);
        parcel.writeInt(mId);
        parcel.writeDouble(mMessage);
        parcel.writeString(mCountry);
        parcel.writeInt(mSunrise);
        parcel.writeInt(mSunset);
    }

    private void initFieldsIfNull(){

        if(mType == null)
            mType = 0;
        if(mId == null)
            mId = 0;
        if(mMessage == null)
            mMessage = 0d;
        if(mCountry == null)
            mCountry = "";
        if(mSunrise == null)
            mSunrise = 0;
        if(mSunset == null)
            mSunset = 0;
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {

        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };

    private Sys(Parcel parcel) {
        mType = parcel.readInt();
        mId = parcel.readInt();
        mMessage = parcel.readDouble();
        mCountry = parcel.readString();
        mSunrise = parcel.readInt();
        mSunset = parcel.readInt();
    }
}
