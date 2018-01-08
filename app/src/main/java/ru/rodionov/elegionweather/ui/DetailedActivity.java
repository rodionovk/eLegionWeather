package ru.rodionov.elegionweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.objects.CityWeather;

public class DetailedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CityWeather mCityWeather = (CityWeather)getIntent().getParcelableExtra(CityWeather.class.getCanonicalName());
        int color = getIntent().getIntExtra(MainActivity.COLOR,0);

        setContentView(R.layout.activity_detailed);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FragmentDetailed frgmtDetailed = FragmentDetailed.newInstance(mCityWeather,color);
        getFragmentManager().beginTransaction().replace(R.id.container,frgmtDetailed).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
