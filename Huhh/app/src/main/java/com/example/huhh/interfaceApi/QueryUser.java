package com.example.huhh.interfaceApi;

import com.example.huhh.requestResponse.UserDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface QueryUser {
    @GET("me")
    Call<UserDetails> userDetails(@Header("authorization") String jwt);

   /* @GET("/grades/44371")
    Call <List<StudentDetails>> studentDetails(@Header("authorization") String jwt);*/

}
