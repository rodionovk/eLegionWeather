
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rain implements Parcelable{

    @SerializedName("1h")
    @Expose
    private Double m1h;

    /**
     * 
     * @return
     *     The _1h
     */
    public Double get1h() {
        return m1h;
    }

    /**
     * 
     * @param _1h
     *     The 1h
     */
    public void set1h(Double _1h) {
        this.m1h = _1h;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();
        parcel.writeDouble(m1h);
    }

    private void initFieldsIfNull(){

        if(m1h == null)
            m1h = 0d;
    }

    public static final Creator<Rain> CREATOR = new Creator<Rain>() {

        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        public Rain[] newArray(int size) {
            return new Rain[size];
        }
    };

    private Rain(Parcel parcel) {
        m1h = parcel.readDouble();
    }
}
