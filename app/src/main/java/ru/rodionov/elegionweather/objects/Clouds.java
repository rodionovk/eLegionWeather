
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds implements Parcelable{

    @SerializedName("all")
    @Expose
    private Integer mAll;

    /**
     * 
     * @return
     *     The all
     */
    public Integer getAll() {
        return mAll;
    }

    /**
     * 
     * @param all
     *     The all
     */
    public void setAll(Integer all) {
        this.mAll = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();
        parcel.writeInt(mAll);
    }

    private void initFieldsIfNull(){

        if(mAll == null)
            mAll = 0;
    }

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {

        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    private Clouds(Parcel parcel) {
        mAll = parcel.readInt();
    }
}
