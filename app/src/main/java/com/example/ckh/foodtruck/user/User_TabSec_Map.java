package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckh.foodtruck.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

/**
 * Created by Ckh on 2016-10-08.
 */
@SuppressLint("ValidFragment")
public class User_TabSec_Map extends Fragment implements OnMapReadyCallback {
    View view;
    Context mContext;
    GoogleMap gmap;
    UiSettings settings;

    public User_TabSec_Map(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.user_tab2_map, container, false);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.user_gmap);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }
}
