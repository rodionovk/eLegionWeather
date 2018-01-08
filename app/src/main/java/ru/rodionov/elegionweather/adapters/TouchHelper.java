package ru.rodionov.elegionweather.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.database.DbManager;
import ru.rodionov.elegionweather.objects.CityWeather;

/**
 * Created by rodionov on 08.08.2016.
 */
public class TouchHelper extends ItemTouchHelper.SimpleCallback {

    private RvWeatherAdapter mAdapter;
    private Context mContext;

    public TouchHelper(RvWeatherAdapter adapter, Context context){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mAdapter = adapter;
        this.mContext = context;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

        final int position = viewHolder.getAdapterPosition();
        AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
        adb.setTitle(mContext.getResources().getString(R.string.delete));
        adb.setMessage(mContext.getResources().getString(R.string.delete_record) + " " + ((RvWeatherAdapter.CityViewHolder)viewHolder).tvCity.getText());
        adb.setPositiveButton(mContext.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                CityWeather cw = mAdapter.getCityWeather(position);
                DbManager manager = new DbManager(mContext);
                manager.deleteCityWeather(cw.getId());
                mAdapter.remove(position);

            }
        });
        adb.setNegativeButton(mContext.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAdapter.notifyItemChanged(position);
            }
        });
        adb.setCancelable(false);
        adb.show();
    }
}
