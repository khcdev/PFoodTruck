package com.hungryduck.foodtruck.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ckj on 2017-05-30.
 */
public class MySharedPreferences {
    private static MySharedPreferences instance = new MySharedPreferences();
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private MySharedPreferences(){
    }

    public static MySharedPreferences getInstance(){
        return instance;
    }

    public int getAppDBVersion(Context mContext){
        sharedPref = mContext.getSharedPreferences("AppStatus",Activity.MODE_PRIVATE);
        return sharedPref.getInt("AppVersion",1);
    }
    public void setAppDBVersion(Context mContext, int DBVersion){
        sharedPref = mContext.getSharedPreferences("AppStatus",Activity.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putInt("AppVersion",DBVersion);
        //비동기 저장
        editor.apply();
    }
    public boolean getImgFirst(Context mContext){
        sharedPref = mContext.getSharedPreferences("AppStatus",Activity.MODE_PRIVATE);
        return sharedPref.getBoolean("ImgFirst",false);
    }
    public void setImgFirst(Context mContext, boolean bValue){
        sharedPref = mContext.getSharedPreferences("AppStatus",Activity.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putBoolean("ImgFirst",bValue);
        editor.apply();
    }
}