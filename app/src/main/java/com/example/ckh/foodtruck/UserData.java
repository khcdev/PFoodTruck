package com.example.ckh.foodtruck;

import android.widget.ImageView;

/**
 * Created by Ckh on 2016-09-11.
 */
public class UserData {
    private String user_id;
    private String user_name;

    public UserData(){
    }
    public UserData(String user_id,String user_name){
        this.user_id=user_id;
        this.user_name=user_name;
    }
    public void setuser_id(String user_id){
        this.user_id=user_id;
    }
    public void setUser_name(String user_name){
        this.user_name=user_name;
    }
    public String getuser_id(){
        return this.user_id;
    }
    public String getUser_name(){
        return this.user_name;
    }
}
