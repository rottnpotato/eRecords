package com.example.huhh.api;

import com.example.huhh.interfaceApi.QueryClasses;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClasses {


    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bisu.edu.ph/api/faculty/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static QueryClasses getClassService(){
        QueryClasses classService = getRetrofit().create(QueryClasses.class);
        return classService;
    }

}
