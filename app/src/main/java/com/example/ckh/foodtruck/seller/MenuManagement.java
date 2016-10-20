package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.ckh.cstview.SellerMenuListviewAdapter;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.Splash;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-02.
 * db가 오픈되면서 초기에 insert된 데이터들을 조건에 맞게
 */
public class MenuManagement extends Activity {
    DBSQLiteOpenHelper helper;
    SQLiteDatabase db;
    private ListView MenuList = null;
    private SellerMenuListviewAdapter ListViewAdapter = null;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_tab2_menumng);

        MenuList=(ListView) findViewById(R.id.all_menu_list);
        ListViewAdapter = new SellerMenuListviewAdapter(this);
        MenuList.setAdapter(ListViewAdapter);

        helper = new DBSQLiteOpenHelper(
                MenuManagement.this,
                GlobalApplication.dbName,
                null,
                1
        );
        db = helper.getReadableDatabase();
        /*Cursor c = db.rawQuery("select * from menu where truck_id=101",null);
        while(c.moveToNext()){
            String truckname = c.getString(1);
            String owner = c.getString(2);
            String imgcode = c.getString(3);
            double score = c.getDouble(4);
            int favorites = c.getInt(5);
        }*/
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.img_chungnyun_m02,null),
                "동파육",
                "8500원",
                "국내산",
                "한돈을 저렴한 가격에 제공함");
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.img_chungnyun_m01,null),
                "레몬크림새우",
                "8000원",
                "국산",
                "맛있는 레몬크림 새우");



        Button adddetail = (Button) findViewById(R.id.seller_btn_menuadded);
        adddetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuManagement.this,Seller_MenuAdd.class);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 50){
            ListViewAdapter.notifyDataSetChanged();
        }
    }

}
