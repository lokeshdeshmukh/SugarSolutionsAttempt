package com.sugar.example.rest;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "https://sugarcosmetics.s3.amazonaws.com/feeds/";
    private static Retrofit retrofit = null;
    public static Context context;

    public static Retrofit getClient() {
        if (retrofit==null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
