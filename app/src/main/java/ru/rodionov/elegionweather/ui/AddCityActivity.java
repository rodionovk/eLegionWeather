package ru.rodionov.elegionweather.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.SlideScaleInOutRightItemAnimator;
import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.adapters.IRvItemClick;
import ru.rodionov.elegionweather.adapters.RvWeatherAdapter;
import ru.rodionov.elegionweather.api.ResponseWeather;
import ru.rodionov.elegionweather.database.DbManager;
import ru.rodionov.elegionweather.loaders.CityFindLoader;
import ru.rodionov.elegionweather.objects.CityWeather;

public class AddCityActivity extends AppCompatActivity
        implements View.OnClickListener, IRvItemClick, LoaderManager.LoaderCallbacks<ResponseWeather> {

    private EditText edtCityName;
    private Button btnSearch;
    private RecyclerView rvCity;
    private ProgressBar pb;

    private RvWeatherAdapter mAdapter;
    private static final int CITY_FIND_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        getLoaderManager().initLoader(CITY_FIND_LOADER,null,this);
    }

    private void initView(){

        setContentView(R.layout.activity_add_city);

        edtCityName = (EditText)findViewById(R.id.edtCityName);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        pb = (ProgressBar) findViewById(R.id.pb);
        btnSearch.setOnClickListener(this);

        rvCity = (RecyclerView)findViewById(R.id.rvCity);
        rvCity.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvCity.setLayoutManager(llm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onClick(View view) {

        if(edtCityName.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.city_name_empty),Toast.LENGTH_SHORT).show();
            return;
        }

        pb.setVisibility(View.VISIBLE);
        rvCity.setVisibility(View.INVISIBLE);

        Bundle bndl = new Bundle();
        bndl.putString(CityFindLoader.CITY,edtCityName.getText().toString());
        getLoaderManager().restartLoader(CITY_FIND_LOADER,bndl,this).forceLoad();
    }

    @Override
    public void onItemClick(View v, int position) {

        CityWeather cw = mAdapter.getCityWeather(position);

        DbManager dbManager = new DbManager(this);
        dbManager.insertCityWeather(cw);

        Intent intent = new Intent();
        intent.putExtra(CityFindLoader.CITY,cw);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<ResponseWeather> onCreateLoader(int i, Bundle bundle) {
        return new CityFindLoader(this,bundle);
    }

    @Override
    public void onLoadFinished(Loader<ResponseWeather> loader, ResponseWeather response) {

        pb.setVisibility(View.GONE);
        rvCity.setVisibility(View.VISIBLE);

        if(response.getRequestResult() == ResponseWeather.ERROR){
            Toast.makeText(this,getResources().getString(R.string.error_connection),Toast.LENGTH_SHORT).show();
            return;
        }

        if(response.getListWeather().size() == 0){
            Toast.makeText(this,getResources().getString(R.string.city_not_found),Toast.LENGTH_SHORT).show();
            return;
        }

        mAdapter = new RvWeatherAdapter(response.getListWeather(),this);

        SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(mAdapter, rvCity);
        rvCity.setAdapter(animatorAdapter);
        rvCity.setItemAnimator(new SlideScaleInOutRightItemAnimator(rvCity));
    }

    @Override
    public void onLoaderReset(Loader<ResponseWeather> loader) {

    }
}
