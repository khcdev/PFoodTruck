package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ckh.listView.TruckCardViewAdapter;
import com.example.ckh.viewdto.TruckItemDTO;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-08.
 */
@SuppressLint("ValidFragment")
public class UserTabFirstList extends Fragment {

    private View view;
    private Context mContext;

    public UserTabFirstList(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.user_tab1_list, null);
        }

        SQLiteDatabase db;
        DBSQLiteOpenHelper helper;
        RecyclerView.LayoutManager mLayoutManager;

        RecyclerView trucklistView;
        TruckCardViewAdapter adapter;
        String imgpath = "data/data/com.example.ckh.foodtruck/files/";

        trucklistView = (RecyclerView) view.findViewById(R.id.user_truck_recycler);
        trucklistView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        trucklistView.setLayoutManager(mLayoutManager);
        if (GlobalApplication.dataList.size() != 0) {
            GlobalApplication.dataList = new ArrayList<>();
        }
        helper = new DBSQLiteOpenHelper(getActivity(), GlobalApplication.dbName, null, 2);
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select truck_name,score,favorites,imgcode,truck_id from foodtruck", null);
        while (c.moveToNext()) {
            Bitmap bm = null;
            TruckItemDTO data = new TruckItemDTO();
            data.setTruck_id(c.getInt(4));
            data.setTruckname(c.getString(0));
            data.setTruckfavor(c.getInt(2));
            data.setTruckscore(c.getDouble(1));
            data.setImgcode(c.getString(3) + ".png");
            data.setTruck_noti(GlobalApplication.trucknoti.get(Integer.toString(data.getTruck_id())));
            try {
                bm = BitmapFactory.decodeFile(imgpath + data.getImgcode());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("fileloadfailed", "비트맵 이미지 불러오기 실패");
            }
            data.setTruckimg(bm);
            GlobalApplication.dataList.add(data);

        }
        db.close();
        c.close();

        adapter = new TruckCardViewAdapter(getActivity(), GlobalApplication.dataList);
        trucklistView.setAdapter(adapter);
        return view;
    }

}