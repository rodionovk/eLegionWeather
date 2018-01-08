
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wind implements Parcelable{

    @SerializedName("speed")
    @Expose
    private Double mSpeed;
    @SerializedName("deg")
    @Expose
    private Double mDeg;

    /**
     * 
     * @return
     *     The speed
     */
    public Double getSpeed() {
        return mSpeed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(Double speed) {
        this.mSpeed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Double getDeg() {
        return mDeg;
    }

    public String getStringDeg(){

        String deg;
        if(mDeg == 0d){
            deg = "Северный";
        } else if(mDeg > 0d && mDeg < 90d){
            deg = "Cеверо-Восточный";
        } else if (mDeg == 90d){
            deg = "Восточный";
        } else if (mDeg > 90d & mDeg < 180d){
            deg = "Юго-Восточный";
        } else if (mDeg == 180d){
            deg = "Южный";
        } else if (mDeg > 180d & mDeg < 270d){
            deg = "Юго-Западный";
        } else if (mDeg == 270){
            deg = "Западный";
        } else {
            deg = "Cеверо-Западный";
        }
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Double deg) {
        this.mDeg = deg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();
        parcel.writeDouble(mSpeed);
        parcel.writeDouble(mDeg);
    }

    private void initFieldsIfNull(){

        if(mSpeed == null)
            mSpeed = 0d;
        if(mDeg == null)
            mDeg = 0d;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {

        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    private Wind(Parcel parcel) {
        mSpeed = parcel.readDouble();
        mDeg = parcel.readDouble();
    }
}
