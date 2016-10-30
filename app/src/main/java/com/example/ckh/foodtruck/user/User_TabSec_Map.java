package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
        }/**
         형 GlobalApplication class에 openStore변수가 true일때만 gopizza 트럭 핀 찍어주시면 될것같습니다.
         if만 걸어주시면 될것같아용
         */
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.user_gmap);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap=googleMap;
        settings = gmap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        /**
         형 GlobalApplication class에 openStore변수가 true일때만 gopizza 트럭 핀 찍어주시면 될것같습니다.
         if만 걸어주시면 될것같아용--> 이 아래 if 구문 참조
         */
        if(GlobalApplication.openStore==true)
        {
            gmap.addMarker(new MarkerOptions().title("gopizza").position(new LatLng( 37.55139, 127.074111)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));
        }
        //청년반점의 좌표는 한양대로 하였음 왕십리 ak플라자는 없어서
        gmap.addMarker(new MarkerOptions().title("청년반점").position(new LatLng( 37.5572321,127.0431332)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));
        gmap.addMarker(new MarkerOptions().title("SteakOut").position(new LatLng( 37.5264467,127.029467)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));

        CameraPosition cp = new CameraPosition.Builder().target(new LatLng(37.5759, 126.9769)).zoom(17).build();
        gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                //마커 클릭시 이벤트 부분 액티비티 단순하게 인텐트 사용해서 넘기면 끝이남

            }
        });
    }
}
