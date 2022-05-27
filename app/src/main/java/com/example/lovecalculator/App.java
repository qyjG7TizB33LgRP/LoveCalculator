package com.example.lovecalculator;

import android.app.Application;

import com.example.lovecalculator.network.LoveApi;
import com.example.lovecalculator.network.RetrofitService;

public class App extends Application {
   public static LoveApi loveApi;


    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService retrofitService = new RetrofitService();
        loveApi = retrofitService.getApi();

    }
}
