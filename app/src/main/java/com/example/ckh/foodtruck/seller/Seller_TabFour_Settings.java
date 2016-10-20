package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-03.
 */

@SuppressLint({"SetJavaScriptEnabled","ValidFragment"})
public class Seller_TabFour_Settings extends Fragment{
    Context mContext;
    public Seller_TabFour_Settings(Context context){
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        View view = inflater.inflate(R.layout.seller_tab4_settings,null);
        return view;
    }
}
