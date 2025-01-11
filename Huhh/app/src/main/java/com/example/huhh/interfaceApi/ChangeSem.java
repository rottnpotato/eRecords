package com.example.huhh.interfaceApi;

import com.example.huhh.requestResponse.ChangeSemRequest;
import com.example.huhh.requestResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ChangeSem {
    @POST
    Call<LoginResponse> changeSem(@Url String url, @Body ChangeSemRequest changeSemRequest);
}
