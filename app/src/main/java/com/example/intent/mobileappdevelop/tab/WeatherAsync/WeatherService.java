package com.example.intent.mobileappdevelop.tab.WeatherAsync;

import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherPojo.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherService {
    @GET
    Call<WeatherData> getWeatherData(@Url String endurl);
}
