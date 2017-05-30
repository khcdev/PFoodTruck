package com.example.ckh.foodtruck.utility;

import retrofit2.Retrofit;

/**
 * Created by ckj on 2017-05-30.
 * Retrofit Singleton class
 * REST 요청을 하기위한 Retrofit class 객체를 리턴하도록 한다.
 */
public class MyRetrofit {
    private static MyRetrofit instance = new MyRetrofit();
    private Retrofit retrofit=null;
    private MyRetrofit(){}

    public static MyRetrofit getInstance(){
        return instance;
    }
    public void setRetrofit(Retrofit retrofit){
        this.retrofit=retrofit;
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
