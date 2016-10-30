package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckh.foodtruck.Main2Activity;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.Splash;
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
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint({"ValidFragment"})
public class Seller_TabFirst_Sales extends Fragment implements OnMapReadyCallback {
    final int GU_SIZE = 25 * 3;
    Context mContext;
    View view;
    GoogleMap gmap;
    UiSettings settings;

    public Seller_TabFirst_Sales(Context context) {
        mContext = context;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.seller_tab1_sales, container, false);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.gmap);
        mapFragment.getMapAsync(this);


        return view;
    }

    // 중복뷰 제거
    // 프래그먼트가 화면에서 사라질때, 컨테이너 뷰(parent)에서 프래그먼트(v)를 제거하여
    // 중복 추가되는 것을 방지하는 원리
    // onDestroyView : 프래그먼트의 계층 뷰가 제거 될때 호출
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
    }


    //맵제어 콜백 메서드
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
        settings = gmap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);

        for (int i = 0; i < Splash.allofseoul.size(); i++) {
            for (int j = 0; j < 5; j++) {
                gmap.addMarker(new MarkerOptions().title(Splash.allofseoul.get(i).get(j).examin_spot_name).position(new LatLng(Splash.allofseoul.get(i).get(j).Ycode, Splash.allofseoul.get(i).get(j).Xcode)).icon(BitmapDescriptorFactory.fromResource(R.drawable.person)));
            }
        }

        CameraPosition cp = new CameraPosition.Builder().target(new LatLng(37.5759, 126.9769)).zoom(13).build();
        gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("ID", marker.getTitle());
                startActivity(intent);
            }
        });
    }
}