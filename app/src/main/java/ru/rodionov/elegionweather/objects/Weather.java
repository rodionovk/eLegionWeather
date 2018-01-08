
package ru.rodionov.elegionweather.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Weather implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("main")
    @Expose
    private String mMain;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("icon")
    @Expose
    private String mIcon;

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
     *     The main
     */
    public String getMain() {
        return mMain;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(String main) {
        this.mMain = main;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.mDescription = description;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return mIcon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        initFieldsIfNull();

        parcel.writeInt(mId);
        parcel.writeString(mMain);
        parcel.writeString(mDescription);
        parcel.writeString(mIcon);
    }

    private void initFieldsIfNull(){

        if(mId == null)
            mId = 0;
        if(mMain == null)
            mMain = "";
        if(mDescription == null)
            mDescription = "";
        if(mIcon == null)
            mIcon = "";
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {

        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    private Weather(Parcel parcel) {
        mId = parcel.readInt();
        mMain = parcel.readString();
        mDescription = parcel.readString();
        mIcon = parcel.readString();
    }
}
