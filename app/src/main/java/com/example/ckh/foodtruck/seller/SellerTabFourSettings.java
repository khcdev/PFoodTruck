package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-03.
 */

@SuppressLint({"SetJavaScriptEnabled","ValidFragment"})
public class SellerTabFourSettings extends Fragment {

    //Context mContext;
    private View view;
    public SellerTabFourSettings(Context context){
        //mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        if(view ==null) {
            view = inflater.inflate(R.layout.seller_tab4_settings,null);
        }

        Switch sch = (Switch) view.findViewById(R.id.openstoreswitch);
        if(GlobalApplication.openStore)sch.setChecked(true);
        sch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    GlobalApplication.openStore = true;
                }else{
                    GlobalApplication.openStore = false;
                }
            }
        });
        return view;
    }
}
