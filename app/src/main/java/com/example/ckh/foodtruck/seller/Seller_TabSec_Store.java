package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint("ValidFragment")
public class Seller_TabSec_Store extends Fragment {
    Context mContext;
    DBSQLiteOpenHelper helper;
    SQLiteDatabase db;
    View view;

    public Seller_TabSec_Store(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.seller_tab2_stroemng, null);
        }
        TextView favor = (TextView) view.findViewById(R.id.seller_store_favorites);
        TextView score = (TextView) view.findViewById(R.id.seller_store_score);
        helper = new DBSQLiteOpenHelper(getActivity(), GlobalApplication.dbName, null, 1);
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select score,favorites from foodtruck where truck_id=102;", null);
        while (c.moveToNext()) {
            favor.setText(c.getInt(1) + " ");
            score.setText(c.getDouble(0) + " ");
        }

        TextView btnMenuMng = (TextView) view.findViewById(R.id.seller_tab2_menumanagement);
        btnMenuMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuManagement.class);
                intent.putExtra("isSeller", true);
                intent.putExtra("truckcode", 102);
                startActivity(intent);
            }
        });
        TextView btnstoreMng = (TextView) view.findViewById(R.id.seller_tab2_introducestore);

        btnstoreMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreManagement.class);
                startActivityForResult(intent, 100);

            }
        });
        Button reviewmore = (Button) view.findViewById(R.id.seller_tab2_reviewmore);
        reviewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReviewMore.class);
                intent.putExtra("Req", 0);
                startActivityForResult(intent, 0);
            }
        });
        db.close();
        return view;
    }
}