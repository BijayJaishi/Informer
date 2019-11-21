package com.heartsun.informer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bijay on 11/20/2019.
 */

public class RetrofitClient {


    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://bhanchha.redirectme.net:8008/informer/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
