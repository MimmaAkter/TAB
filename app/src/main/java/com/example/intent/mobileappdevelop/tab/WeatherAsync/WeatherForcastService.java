package com.example.intent.mobileappdevelop.tab.WeatherAsync;

import com.example.intent.mobileappdevelop.tab.WeatherAsync.ForecastWeatherPojo.WeatherForecastData;
import com.example.intent.mobileappdevelop.tab.WeatherAsync.WeatherPojo.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherForcastService {
    @GET
    Call<WeatherForecastData> getWeatherData(@Url String endurl);
}
