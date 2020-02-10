package com.example.movietask.webservice;

import android.app.Application;

import com.example.movietask.constants.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Application {

    private static RetrofitClient instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public ApiService createMovieService() {
        return initRetrofit().create(ApiService.class);
    }

    private Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
