package com.example.intent.mobileappdevelop.tab.WeatherAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intent.mobileappdevelop.tab.R;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.ForecastWeatherPojo.List;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.ForecastWeatherPojo.WeatherForecastData;

public class ForecastAdapter extends RecyclerView.Adapter<Holder> {
    private Context context;
    private WeatherForecastData forecastData;

    public ForecastAdapter(Context context, WeatherForecastData forecastData) {
        this.context = context;
        this.forecastData = forecastData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.layout,viewGroup,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
       List lists = forecastData.getList().get(i);
        holder.minText.setText("TempMax: "+String.valueOf(lists.getTemp().getMax()+"\nTempMin: "+String.valueOf(lists.getTemp().getMin())));
        holder.maxText.setText("Humidity: "+String.valueOf(lists.getHumidity()+" %"));
    }

    @Override
    public int getItemCount() {
        return forecastData.getList().size();
    }
}
class Holder extends RecyclerView.ViewHolder {

    TextView minText;
    TextView maxText;
    public Holder(@NonNull View itemView) {
        super(itemView);
        minText = itemView.findViewById(R.id.minTemp_row);
        maxText = itemView.findViewById(R.id.maxTemp_row);
    }
}
