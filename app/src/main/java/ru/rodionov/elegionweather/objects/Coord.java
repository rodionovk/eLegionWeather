
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Coord implements Parcelable{

    @SerializedName("lon")
    @Expose
    private Double mLon;
    @SerializedName("lat")
    @Expose
    private Double mLat;

    /**
     * 
     * @return
     *     The lon
     */
    public Double getLon() {
        return mLon;
    }

    /**
     * 
     * @param lon
     *     The lon
     */
    public void setLon(Double lon) {
        this.mLon = lon;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return mLat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.mLat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();
        parcel.writeDouble(mLon);
        parcel.writeDouble(mLat);
    }

    private void initFieldsIfNull(){

        if(mLon == null)
            mLon = 0d;
        if(mLat == null)
            mLat = 0d;
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {

        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };

    private Coord(Parcel parcel) {
        mLon = parcel.readDouble();
        mLat = parcel.readDouble();
    }
}
