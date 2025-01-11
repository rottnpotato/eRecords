package com.example.huhh.interfaceApi;

import com.example.huhh.ClassDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface QueryClasses {
    @GET("grades/classes")
    Call<List<ClassDetails>> classesDetails(@Header("authorization") String jwt);


}
