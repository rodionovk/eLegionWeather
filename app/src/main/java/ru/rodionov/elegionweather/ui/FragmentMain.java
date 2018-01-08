package ru.rodionov.elegionweather.ui;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.adapters.IRvItemClick;
import ru.rodionov.elegionweather.adapters.RvWeatherAdapter;
import ru.rodionov.elegionweather.adapters.TouchHelper;
import ru.rodionov.elegionweather.api.ResponseWeather;
import ru.rodionov.elegionweather.loaders.WeatherLoader;
import ru.rodionov.elegionweather.objects.CityWeather;

/**
 * Created by rodionov on 10.08.2016.
 */
public class FragmentMain extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<ResponseWeather>,IRvItemClick {

    private RvWeatherAdapter mAdapter;
    private RecyclerView rvCity;
    private ProgressBar mPb;
    private SwipeRefreshLayout swipeRefreshLayout;

    private static final int WEATHER_LOADER = 1;


    public interface onItemClickEventListener {
        void onItemClick(View view,CityWeather c,int color);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(WEATHER_LOADER,null,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        mPb = (ProgressBar)view.findViewById(R.id.pb);
        rvCity = (RecyclerView)view.findViewById(R.id.rvCity);
        rvCity.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvCity.setLayoutManager(llm);

        return view;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getLoaderManager().getLoader(WEATHER_LOADER).forceLoad();
    }

    @Override
    public Loader<ResponseWeather> onCreateLoader(int i, Bundle bundle) {
        return new WeatherLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ResponseWeather> loader, ResponseWeather responseWeather) {

        if(responseWeather.getRequestResult() == ResponseWeather.ERROR){
            Toast.makeText(getActivity(),getResources().getString(R.string.error_connection),Toast.LENGTH_SHORT).show();
        }

        swipeRefreshLayout.setRefreshing(false);
        mPb.setVisibility(View.GONE);
        rvCity.setVisibility(View.VISIBLE);

        if(mAdapter == null)
            mAdapter = new RvWeatherAdapter(responseWeather.getListWeather(), this);
        else
            mAdapter.setListWeather(responseWeather.getListWeather());

        SlideInBottomAnimatorAdapter animatorAdapter = new SlideInBottomAnimatorAdapter(mAdapter, rvCity);
        rvCity.setAdapter(animatorAdapter);
        rvCity.setItemAnimator(new SlideInOutLeftItemAnimator(rvCity));

        ItemTouchHelper.Callback callback = new TouchHelper(mAdapter,getActivity());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvCity);
    }

    @Override
    public void onLoaderReset(Loader<ResponseWeather> loader) {

    }

    @Override
    public void onItemClick(View v, int position) {

        CityWeather cityweather = mAdapter.getCityWeather(position);
        ((onItemClickEventListener)getActivity()).onItemClick(v,cityweather,(int)v.findViewById(R.id.img).getTag());
    }

    public void addItem(final CityWeather cityweather){

        mAdapter.addCityWeather(cityweather,mAdapter.getItemCount());
        rvCity.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }
}
