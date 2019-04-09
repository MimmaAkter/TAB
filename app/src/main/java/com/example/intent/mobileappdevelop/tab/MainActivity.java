package com.example.intent.mobileappdevelop.tab;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FusedLocationProviderClient locationProviderClient;
    private boolean isLocationFind =false;
    private static final int REQUEST_CODE = 70;
    private double Latitude;
    private double Longitude;
    private CurrentWeatherfragment currentWeatherfragment;
    private ForecaseWatherFragment forecaseWatherFragment;
    private LocationFoundListiner foundListinerCurrent;
    private LocationFoundListiner foundListinerForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_wb_sunny_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_list_black_24dp));
        tabLayout.setTabTextColors(Color.WHITE, Color.GREEN);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        Toast.makeText(this, "Git test ttt444 ", Toast.LENGTH_SHORT).show();
        currentWeatherfragment =
                new CurrentWeatherfragment();
        forecaseWatherFragment =
                new ForecaseWatherFragment();
        foundListinerCurrent = currentWeatherfragment;
        foundListinerForecast = currentWeatherfragment;


        locationProviderClient =
                LocationServices.getFusedLocationProviderClient(MainActivity.this);
        getPermission();

        WeatherPagerAdapter weatherPagerAdapter =
                new WeatherPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(weatherPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },REQUEST_CODE);

        }else {
            isLocationFind =true;
            getLocation();
        }

    }

    private void getLocation(){

            locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Latitude = location.getLatitude();
                    Longitude = location.getLongitude();
                  //  Toast.makeText(MainActivity.this, "found", Toast.LENGTH_SHORT).show();
                    foundListinerForecast.getLocation(Latitude, Longitude);
                    foundListinerCurrent.getLocation(Latitude,Longitude);

                    // Toast.makeText(MainActivity.this, String.valueOf(Latitude)+String.valueOf(Longitude), Toast.LENGTH_SHORT).show();
                }
            });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==REQUEST_CODE){
            if(grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                isLocationFind = true;
                getLocation();
            }
        }
    }

    private class WeatherPagerAdapter extends FragmentPagerAdapter{

        public WeatherPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            switch (i){
                case 0:{
                    return currentWeatherfragment;
                }
                case 1:{
                    return forecaseWatherFragment;
                }
                default:{
                    return currentWeatherfragment;
                }
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
