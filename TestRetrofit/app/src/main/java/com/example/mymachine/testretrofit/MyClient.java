package com.example.mymachine.testretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by myMachine on 10/28/2016.
 */

public class MyClient {

    public static final String BASE_URL= "http://ivyinfoserve.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            
//             HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//             logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//             OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            
//              if (BuildConfig.DEBUG) {
//                  httpClient.addInterceptor(logging);
//              }
            
//             Gson gson = new GsonBuilder()
//                     .setLenient()
//                     .create();
            
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                     .addConverterFactory(GsonConverterFactory.create(gson))
//                     .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
