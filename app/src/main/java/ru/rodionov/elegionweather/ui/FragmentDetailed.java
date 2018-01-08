package ru.rodionov.elegionweather.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.squareup.picasso.Picasso;

import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.objects.CityWeather;

/**
 * Created by rodionov on 10.08.2016.
 */
public class FragmentDetailed extends Fragment {

    private ImageView img;
    private TextView tvCity;
    private TextView tvInfo;
    private TextView tvTemp;
    private ImageView icon;

    private TextView tvWindSpeed;
    private TextView tvPressure;
    private TextView tvHumidity;
    private TextView tvClouds;
    private TextView tvWindRose;

    private CityWeather mCityWeather;
    private int mColor;

    public static FragmentDetailed newInstance(CityWeather cityweather,int color){

        FragmentDetailed fragment = new FragmentDetailed();

        Bundle arguments = new Bundle();
        arguments.putParcelable(CityWeather.class.getCanonicalName(),cityweather);
        arguments.putInt(MainActivity.COLOR,color);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        mCityWeather = arguments.getParcelable(CityWeather.class.getCanonicalName());
        mColor = arguments.getInt(MainActivity.COLOR,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_detailed, container, false);

        img = (ImageView)view.findViewById(R.id.img);
        tvCity = (TextView)view.findViewById(R.id.tvCity);
        icon = (ImageView)view.findViewById(R.id.icon);
        tvInfo = (TextView)view.findViewById(R.id.tvInfo);
        tvTemp = (TextView)view.findViewById(R.id.tvTemp);
        tvWindSpeed = (TextView)view.findViewById(R.id.tvWindSpeed);
        tvPressure = (TextView)view.findViewById(R.id.tvPressure);
        tvHumidity = (TextView)view.findViewById(R.id.tvHumidity);
        tvClouds = (TextView)view.findViewById(R.id.tvClouds);
        tvWindRose = (TextView)view.findViewById(R.id.tvWindRose);

        if(mCityWeather != null){

            String letter = mCityWeather.getName().substring(0,1).toUpperCase();
            TextDrawable drawable = TextDrawable.builder().buildRound(letter, mColor);
            img.setImageDrawable(drawable);

            tvCity.setText(mCityWeather.getName());
            Picasso.with(getActivity()).load(mCityWeather.getUrlPic()).into(icon);
            tvInfo.setText(mCityWeather.getWeather().get(0).getDescription());
            tvTemp.setText(mCityWeather.getMain().getRoundTemp() + "°C");
            tvWindSpeed.setText(mCityWeather.getWind().getSpeed() + " м/с");
            tvPressure.setText(mCityWeather.getMain().getRoundPressure() + " мм рт.ст.");
            tvHumidity.setText(mCityWeather.getMain().getHumidity() + "%");
            tvClouds.setText(mCityWeather.getClouds().getAll() + "%");
            tvWindRose.setText(mCityWeather.getWind().getStringDeg());
        }

        return view;
    }

}
