package com.example.huhh.interfaceApi;

import com.example.huhh.requestResponse.LoginRequest;
import com.example.huhh.requestResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

}
