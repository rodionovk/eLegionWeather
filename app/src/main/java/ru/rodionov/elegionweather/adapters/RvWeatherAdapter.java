package ru.rodionov.elegionweather.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.rodionov.elegionweather.R;
import ru.rodionov.elegionweather.objects.CityWeather;
import ru.rodionov.elegionweather.objects.ColorGenerator;

/**
 * Created by rodionov on 08.08.2016.
 */
public class RvWeatherAdapter extends RecyclerView.Adapter<RvWeatherAdapter.CityViewHolder>{

    private ArrayList<CityWeather> mListWeather;
    private IRvItemClick mItemClick;

    public RvWeatherAdapter(ArrayList<CityWeather> list, IRvItemClick itemclicklistner){
        this.mListWeather = list;
        this.mItemClick = itemclicklistner;
    }

    public void setListWeather(ArrayList<CityWeather> list){
        this.mListWeather = list;
    }

    public void addCityWeather(CityWeather weather,int pos){
        mListWeather.add(pos,weather);
        notifyItemInserted(pos);
    }

    public CityWeather getCityWeather(int position){
        return mListWeather.get(position);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        final CityViewHolder holder = new CityViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mItemClick.onItemClick(v, holder.getAdapterPosition());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {

        CityWeather weather = mListWeather.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        if(weather.getName().length() == 0){
            weather.setName("UNKNOW");
        }

        String letter = weather.getName().substring(0,1).toUpperCase();
        TextDrawable drawable = TextDrawable.builder().buildRound(letter, color);

        holder.img.setImageDrawable(drawable);
        holder.img.setTag(color);
        holder.tvCity.setText(weather.getName());
        holder.tvInfo.setText(weather.getWeather().get(0).getDescription());
        holder.tvTemp.setText(weather.getMain().getRoundTemp() + "°C ");

        Picasso.with(holder.icon.getContext()).load(weather.getUrlPic()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mListWeather.size();
    }

    public void remove(int position) {
        mListWeather.remove(position);
        notifyItemRemoved(position);
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tvCity;
        TextView tvTemp;
        TextView tvInfo;
        ImageView icon;

        public CityViewHolder(View itemView){
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.img);
            tvCity = (TextView)itemView.findViewById(R.id.tvCity);
            tvTemp = (TextView)itemView.findViewById(R.id.tvTemp);
            tvInfo = (TextView)itemView.findViewById(R.id.tvInfo);
            icon = (ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
