package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint("ValidFragment")
public class Seller_TabSec_Store extends Fragment {
    Context mContext;
    public Seller_TabSec_Store(Context context){
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.seller_tab2_stroemng,null);

        TextView btnMenuMng = (TextView) view.findViewById(R.id.seller_tab2_menumanagement);
        btnMenuMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MenuManagement.class);
                startActivity(intent);
            }
        });
        Button reviewmore = (Button) view.findViewById(R.id.seller_tab2_reviewmore);
        reviewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReviewMore.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
