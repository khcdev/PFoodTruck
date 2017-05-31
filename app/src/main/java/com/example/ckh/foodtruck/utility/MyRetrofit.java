package com.example.ckh.foodtruck.utility;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ckj on 2017-05-30.
 * Retrofit Singleton class
 * REST 요청을 하기위한 Retrofit class 객체를 리턴하도록 한다.
 */
public class MyRetrofit {
    private static MyRetrofit instance = new MyRetrofit();
    private Retrofit retrofit;
    private MyRetrofit(){
        buildRetrofit();
    }
    private void buildRetrofit(){
        retrofit = new Retrofit.Builder()
                                .baseUrl("http://52.78.234.100:3040/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
    }
    public static MyRetrofit getInstance(){
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
