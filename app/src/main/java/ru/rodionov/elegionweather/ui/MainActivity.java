package ru.rodionov.elegionweather.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.FrameLayout;

import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.loaders.CityFindLoader;
import ru.rodionov.elegionweather.objects.CityWeather;

public class MainActivity extends AppCompatActivity
        implements FragmentMain.onItemClickEventListener, View.OnClickListener {

    private static final int ADD_CITY = 0;
    public static final String COLOR = "color";

    private boolean mIsTablet;
    private FragmentMain mFragmentMain;
    private FrameLayout mContainerDetailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState){

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);

        mContainerDetailed = (FrameLayout) findViewById(R.id.containerDetailed);
        mIsTablet =  mContainerDetailed != null ? true : false;

        mFragmentMain = new FragmentMain();
        getFragmentManager().beginTransaction()
                .replace(R.id.containerMain, mFragmentMain)
                .commit();
    }

    @Override
    public void onItemClick(View view,CityWeather cityweather,int color) {

        if (mIsTablet){

            FragmentDetailed frgmtDetailed = FragmentDetailed.newInstance(cityweather,color);
            FragmentTransaction frTransaction = getFragmentManager().beginTransaction();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Transition changeTransform = TransitionInflater.from(this).inflateTransition(R.transition.change_transform);
                Transition explodeTransform = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);

                mFragmentMain.setSharedElementReturnTransition(changeTransform);
                mFragmentMain.setExitTransition(explodeTransform);

                frgmtDetailed.setSharedElementEnterTransition(changeTransform);
                frgmtDetailed.setEnterTransition(explodeTransform);

                frTransaction.addSharedElement(view.findViewById(R.id.tvCity),getResources().getString(R.string.transition_city))
                        .addSharedElement(view.findViewById(R.id.tvInfo),getResources().getString(R.string.transition_info))
                        .addSharedElement(view.findViewById(R.id.tvTemp),getResources().getString(R.string.transition_temp))
                        .addSharedElement(view.findViewById(R.id.icon),getResources().getString(R.string.transition_icon));
            }

            frTransaction.replace(R.id.containerDetailed,frgmtDetailed).commit();


        } else {

            Intent intent = new Intent(this,DetailedActivity.class);
            intent.putExtra(CityWeather.class.getCanonicalName(),cityweather);
            intent.putExtra(COLOR,color);

            Pair<View,String> p1 = Pair.create(view.findViewById(R.id.tvCity),getResources().getString(R.string.transition_city));
            Pair<View,String> p2 = Pair.create(view.findViewById(R.id.tvInfo),getResources().getString(R.string.transition_info));
            Pair<View,String> p3 = Pair.create(view.findViewById(R.id.tvTemp),getResources().getString(R.string.transition_temp));
            Pair<View,String> p4 = Pair.create(view.findViewById(R.id.icon),getResources().getString(R.string.transition_icon));

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1,p2,p3,p4);
            startActivity(intent,optionsCompat.toBundle());

        }

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,AddCityActivity.class);
        startActivityForResult(intent, ADD_CITY);
    }

     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_CITY && resultCode == RESULT_OK){
            CityWeather cw = data.getParcelableExtra(CityFindLoader.CITY);
            mFragmentMain.addItem(cw);
        }
    }
}
