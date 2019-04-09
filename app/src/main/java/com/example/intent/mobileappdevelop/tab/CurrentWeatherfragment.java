package com.example.intent.mobileappdevelop.tab;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherClient;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherPojo.WeatherData;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherfragment extends Fragment implements LocationFoundListiner {

    private double latitude;
    private double longitude;
    private boolean isLocationFound;
    private TextView textView;
    private String baseUrl = "https://api.openweathermap.org/data/2.5/";
    private ImageView imageView;
    public CurrentWeatherfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weatherfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textrrrr);
        imageView = view.findViewById(R.id.imgView);

    }

    @Override
    public void getLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        String endurl = String.format("weather?lat=%f&lon=%f&units=%s&appid=%s",
                latitude,longitude,"metric",getString(R.string.weatherApiKey));
        WeatherService service = WeatherClient
                .getClient(baseUrl)
                .create(WeatherService.class);
        service.getWeatherData(endurl)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        if(response.isSuccessful()){
                            WeatherData weather = response.body();
                            Double temp =  weather.getMain().getTemp();
                            Double humidity = weather.getMain().getHumidity();
                            Picasso.get().load(
                                    "https://openweathermap.org/img/w/"+
                                    weather.getWeather().get(0).getIcon()+".png").into(imageView);
                           // textView.setText(weather.getWeather().toString());
                            textView.setText("Temp: "+String.valueOf(temp)+"\n"+"Humidity: "+String.valueOf(humidity));
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                     //   Toast.makeText(getActivity(), , Toast.LENGTH_LONG).show();
                        textView.setText(t.getLocalizedMessage());
                    }
                });

    }
}
