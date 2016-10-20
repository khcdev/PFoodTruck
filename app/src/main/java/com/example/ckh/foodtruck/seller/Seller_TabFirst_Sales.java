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
import com.nhn.android.maps.NMapActivity;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint({"ValidFragment"})
public class Seller_TabFirst_Sales extends Fragment {
    Context mContext;

    public Seller_TabFirst_Sales(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.seller_tab1_sales,null);
        return view;
    }
}
