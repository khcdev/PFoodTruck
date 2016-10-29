package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-28.
 */
@SuppressLint("ValidFragment")
public class User_TabTri_settings extends Fragment {

    Context mContext;
    View view;
    TextView tv1;
    LinearLayout v1,v2,v3;
    public User_TabTri_settings(Context context){
        mContext =context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(view==null){
            view = inflater.inflate(R.layout.user_tab3_settings,null);
        }/*
        tv1=(TextView) view.findViewById(R.id.user_id);
        tv1.setText(GlobalApplication.User_info_name);

        v1=(LinearLayout)view.findViewById(R.id.favor_truck_item1);
        v2=(LinearLayout)view.findViewById(R.id.favor_truck_item2);
        v3=(LinearLayout)view.findViewById(R.id.favor_truck_item3);

        if(GlobalApplication.favor_101) v1.setVisibility(View.VISIBLE);
        if(GlobalApplication.favor_102) v2.setVisibility(View.VISIBLE);
        if(GlobalApplication.favor_103) v3.setVisibility(View.VISIBLE);*/
        return view;
    }
}
