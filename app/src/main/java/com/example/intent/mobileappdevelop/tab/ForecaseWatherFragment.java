package com.example.intent.mobileappdevelop.tab;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intent.mobileappdevelop.tab.WeatherAdapter.ForecastAdapter;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.ForecastWeatherPojo.WeatherForecastData;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherClient;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherForcastService;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherPojo.WeatherData;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForecaseWatherFragment extends Fragment implements LocationFoundListiner {


    private String baseUrl = "https://samples.openweathermap.org/data/2.5/";
    private RecyclerView recyclerView;

    public ForecaseWatherFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecase_wather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);

    }

    @Override
    public void getLocation(double latitude, double longitude) {


        final String endurl = String.format("forecast/daily?lat=%f&lon=%f&cnt=%f&appid=%s",
                latitude,longitude,7.0f,getString(R.string.weatherApiKey));


        WeatherForcastService service = WeatherClient
                .getClient(baseUrl)
                .create(WeatherForcastService.class);
        service.getWeatherData(endurl)
                .enqueue(new Callback<WeatherForecastData>() {
                    @Override
                    public void onResponse(Call<WeatherForecastData> call, Response<WeatherForecastData> response) {
                        if(response.isSuccessful()){
                            if(response.body() !=null) {
                                recyclerView.setAdapter(
                                        new ForecastAdapter(getActivity(),
                                                response.body()

                                        ));
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }else {
                                Toast.makeText(getActivity(), "Problem", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherForecastData> call, Throwable t) {

                    }
                });
    }

}
