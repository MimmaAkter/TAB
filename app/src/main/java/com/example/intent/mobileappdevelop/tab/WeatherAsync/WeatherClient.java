package com.example.intent.mobileappdevelop.tab.WeatherAsync;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class WeatherClient {
    public static Retrofit getClient(@Url String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
