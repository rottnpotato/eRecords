package com.example.huhh.api;

import com.example.huhh.interfaceApi.ChangeSem;
import com.example.huhh.interfaceApi.QueryStudents;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiQueryy {
    private static Retrofit getRetrofitt(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofitt = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bisu.edu.ph/api/faculty/")
                .client(okHttpClient)
                .build();
        return retrofitt;
    }

    public static QueryStudents getQueryStudentService(){
        return getRetrofitt().create(QueryStudents.class);
    }
    public static ChangeSem changeSem(){
        return getRetrofitt().create(ChangeSem.class);
    }
}
