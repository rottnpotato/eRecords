package com.example.huhh.interfaceApi;

import com.example.huhh.StudentDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;


public interface QueryStudents {

    @GET
    //Call <StudentDetails> studentDetails(@Url String url, @Header("authorization") String jwt);
    Call <StudentDetails> studentDetails(@Url String url, @Header("authorization") String jwt);

}
