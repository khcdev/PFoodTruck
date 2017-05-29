package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ckh.foodtruck.externLibrary.SpotDetail;
import com.example.ckh.foodtruck.R;
import com.example.ckh.ViewDTO.SpotInformDTO;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint({"ValidFragment"})
public class SellerTabFirstSales extends Fragment implements OnMapReadyCallback {
    final int GU_SIZE = 25 * 3;
    Context mContext;
    View view;
    GoogleMap gmap;
    UiSettings settings;
    ArrayList<SpotInformDTO> spotInformDTOList;

    public SellerTabFirstSales(Context context) {
        mContext = context;
    }

    public SellerTabFirstSales(Context context, ArrayList<SpotInformDTO> spotInformDTOList){
        mContext = context;
        this.spotInformDTOList = spotInformDTOList;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.seller_tab1_sales, container, false);
            AlertDialog.Builder noti = new AlertDialog.Builder(getActivity());
            /*noti.setMessage("");
            noti.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            noti.show();*/
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
        Log.e("spotnumber",Integer.toString(spotInformDTOList.size()));
        for(int i = 0; i< spotInformDTOList.size(); i++){
            gmap.addMarker(new MarkerOptions()
                    .title(spotInformDTOList.get(i).getSPOT_NAME())
                    .position(new LatLng(spotInformDTOList.get(i).getY_POS(), spotInformDTOList.get(i).getX_POS()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.person))
            );
        }
        /**before refactor*/
        /*
        for (int i = 0; i < Splash.allofseoul.size(); i++) {
            for (int j = 0; j < 5; j++) {
                gmap.addMarker(new MarkerOptions()
                        .title(Splash.allofseoul.get(i).get(j).examin_spot_name)
                        .position(new LatLng(Splash.allofseoul.get(i).get(j).Ycode, Splash.allofseoul.get(i).get(j).Xcode))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.person)));
            }
        }*/

        CameraPosition cp = new CameraPosition.Builder().target(new LatLng(37.5759, 126.9769)).zoom(13).build();
        gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                boolean check=false;
                Intent intent = new Intent(getActivity(), SpotDetail.class);

                for(int i = 0; i< spotInformDTOList.size(); i++){
                    if(marker.getTitle().equals(spotInformDTOList.get(i).getSPOT_NAME())){
                        check=true;
                        intent.putExtra("SpotInfoObj", spotInformDTOList.get(i));
                    }
                }
                intent.putExtra("check",check);
                startActivity(intent);
            }
        });
    }
}