package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ckh.foodtruck.Nmap.NMapFragment;
import com.example.ckh.foodtruck.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint({"ValidFragment"})
public class Seller_TabFirst_Sales extends NMapFragment {
    Context mContext;
    NMapView mapView;
    final String CLIENT_ID = "6E_azAG0UscyQ0Vg9JgQ";
    public Seller_TabFirst_Sales(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.seller_tab1_sales,null);

        mapView = (NMapView)view.findViewById(R.id.nMapView);
        mapView.setClientId(CLIENT_ID);
        mapView.setClickable(true);
        mapView.setEnabled(true);
        mapView.setFocusable(true);
        mapView.setFocusableInTouchMode(true);
        mapView.requestFocus();
        return view;
    }
}
