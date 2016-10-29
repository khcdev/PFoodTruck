package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-03.
 */

@SuppressLint({"SetJavaScriptEnabled","ValidFragment"})
public class Seller_TabFour_Settings extends Fragment{
    Context mContext;
    View view;
    Switch sch;
    public Seller_TabFour_Settings(Context context){
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        if(view ==null) {
            view = inflater.inflate(R.layout.seller_tab4_settings,null);
        }
        sch = (Switch) view.findViewById(R.id.openstoreswitch);
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
