package com.example.mymachine.testretrofit;

import com.example.mymachine.testretrofit.Model.ParlorUser;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by myMachine on 10/28/2016.
 */

public interface MyInterface {

    public static final String BASE_URL= "http://ivyinfoserve.com/api/";

    @GET("user")
    Call<ParlorUser> getSuccessDetails();




}
