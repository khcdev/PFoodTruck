package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ckh.cstview.TruckCardViewAdapter;
import com.example.ckh.cstview.TruckItem;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

/**
 * Created by Ckh on 2016-10-08.
 */
@SuppressLint("ValidFragment")
public class User_TabFirst_List extends Fragment {
    SQLiteDatabase db;
    DBSQLiteOpenHelper helper;
    RecyclerView trucklistView;
    TruckCardViewAdapter adapter;
    String imgpath = "data/data/com.example.ckh.foodtruck/files/";
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    Context mContext;
    public User_TabFirst_List(Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(view == null) {
         view = inflater.inflate(R.layout.user_tab1_list, null);
        }

        trucklistView = (RecyclerView) view.findViewById(R.id.user_truck_recycler);
        trucklistView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        trucklistView.setLayoutManager(mLayoutManager);
        if(GlobalApplication.flag_truckinfolist){
            helper = new DBSQLiteOpenHelper(getActivity(), GlobalApplication.dbName,null,1);
            db= helper.getReadableDatabase();
            Cursor c = db.rawQuery("select truck_name,score,favorites,imgcode,truck_id from foodtruck",null);
            while(c.moveToNext()) {
                Bitmap bm = null;
                TruckItem data = new TruckItem();
                data.truck_id = c.getInt(4);
                data.truckname = c.getString(0);
                data.truckfavor = c.getInt(2);
                data.truckscore = c.getDouble(1);
                data.imgcode = c.getString(3) + ".png";
                data.truck_noti = GlobalApplication.trucknoti.get(Integer.toString(data.truck_id));
                try {
                    bm = BitmapFactory.decodeFile(imgpath + data.imgcode);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("fileloadfailed", "비트맵 이미지 불러오기 실패");
                }
                data.truckimg = bm;
                GlobalApplication.dataList.add(data);
            }
            GlobalApplication.flag_truckinfolist=false;
        }

        adapter = new TruckCardViewAdapter(getActivity(), GlobalApplication.dataList);
        trucklistView.setAdapter(adapter);
        return view;
    }
}
