package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ckh.ViewDTO.TruckItemDTO;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

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

        gmap = googleMap;
        settings = gmap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        if (GlobalApplication.openStore == true) {
            gmap.addMarker(new MarkerOptions().title("GoPizza").position(new LatLng(37.55139, 127.074111)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));
        }

        gmap.addMarker(new MarkerOptions().title("청년반점").position(new LatLng(37.5572321, 127.0431332)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));
        gmap.addMarker(new MarkerOptions().title("SteakOut").position(new LatLng(37.5264467, 127.029467)).icon(BitmapDescriptorFactory.fromResource(R.drawable.truck)));

        CameraPosition cp = new CameraPosition.Builder().target(new LatLng(37.5759, 127.0331332)).zoom(12).build();
        gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String trname = marker.getTitle();
                Log.i("ckhtag", trname);
                for (int i = 0; i < GlobalApplication.dataList.size(); i++) {
                    TruckItemDTO a;
                    a = GlobalApplication.dataList.get(i);
                    if (a.getTruckname().equals(trname)) {
                        Intent intent = new Intent(getActivity(), User_TruckInfo.class);
                        intent.putExtra("truckname", a.getTruckname());
                        intent.putExtra("truckid", a.getTruck_id());
                        intent.putExtra("truckfavor", a.getTruckfavor());
                        intent.putExtra("truckscore", a.getTruckscore());
                        intent.putExtra("trucknoti", a.getTruck_noti());
                        intent.putExtra("imgcode", a.getImgcode());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            }
        });

    }
}
