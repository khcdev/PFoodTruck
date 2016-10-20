package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.Suppress;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-08.
 */
@SuppressLint("ValidFragment")
public class User_TabSec_Map extends Fragment {

    Context mContext;
    public User_TabSec_Map (Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.user_tab2_map,null);


        return view;
    }
}
