package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-28.
 */
@SuppressLint("ValidFragment")
public class User_TabTri_settings extends Fragment {

    Context mContext;

    public User_TabTri_settings(Context context){
        mContext =context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.user_tab3_settings,null);
        return view;
    }
}
