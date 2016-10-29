package com.example.ckh.foodtruck.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ckh.cstview.TruckItem;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.seller.MenuManagement;
import com.example.ckh.foodtruck.seller.ReviewMore;

/**
 * Created by Ckh on 2016-10-29.
 */
public class User_TruckInfo extends Activity {
    ImageView truckimg,iv_inrto,iv_menulist,iv_addfavor;
    TextView tv_intro,tv_favor,tv_score,tv_noti;
    Button reviewmore;
    String imgpath = "data/data/com.example.ckh.foodtruck/files/";
    Bitmap bm;
    boolean flag=false;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truckinfo_detail);
        Intent intent = getIntent();
        final TruckItem truckdata = new TruckItem();
        truckdata.truck_id = intent.getExtras().getInt("truckid");
        truckdata.truckname= intent.getExtras().getString("truckname");
        truckdata.truckfavor=intent.getExtras().getInt("truckfavor");
        truckdata.truckscore=intent.getExtras().getDouble("truckscore");
        truckdata.truck_noti=intent.getExtras().getString("trucknoti");
        truckdata.imgcode=intent.getExtras().getString("imgcode");
        try {
            bm = BitmapFactory.decodeFile(imgpath + truckdata.imgcode);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("fileloadfailed","비트맵 이미지 불러오기 실패");
        }
        truckimg = (ImageView) findViewById(R.id.user_store_mainimg);
        iv_inrto =(ImageView) findViewById(R.id.user_truck_detail_notice);
        tv_noti=(TextView) findViewById(R.id.user_truck_detail_noticetext);
        tv_intro=(TextView) findViewById(R.id.user_truck_detail_Introtext);
        iv_menulist=(ImageView) findViewById(R.id.user_truck_detail_menulist);
        tv_favor=(TextView) findViewById(R.id.user_truck_detail_favortext);
        tv_score=(TextView) findViewById(R.id.user_truck_detail_scoretext);
        iv_addfavor=(ImageView) findViewById(R.id.user_addfavor);
        reviewmore = (Button) findViewById(R.id.user_reviewmore);

        truckimg.setImageBitmap(bm);
        iv_inrto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_intro = new AlertDialog.Builder(User_TruckInfo.this);
                alert_intro.setTitle("공지 사항");
                alert_intro.setMessage(GlobalApplication.trucknoti.get(Integer.toString(truckdata.truck_id)));
                alert_intro.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert_intro.show();
            }
        });
        iv_menulist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_TruckInfo.this, MenuManagement.class);
                intent.putExtra("isSeller",false);
                intent.putExtra("truckcode",truckdata.truck_id);
                startActivity(intent);
            }
        });
        tv_intro.setText(GlobalApplication.truckintro.get(Integer.toString(truckdata.truck_id)));
        tv_favor.setText(Integer.toString(truckdata.truckfavor));
        tv_score.setText(Double.toString(truckdata.truckscore));
        reviewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_TruckInfo.this, ReviewMore.class);
                intent.putExtra("Req",1);
                intent.putExtra("truckcode",truckdata.truck_id);
                startActivity(intent);
            }
        });
        iv_addfavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                DBSQLiteOpenHelper helper;
                helper = new DBSQLiteOpenHelper(User_TruckInfo.this, GlobalApplication.dbName, null, 1);
                db = helper.getWritableDatabase();
                switch (truckdata.truck_id) {
                    case 101:
                        if (GlobalApplication.favor_101) {
                            Toast.makeText(User_TruckInfo.this, "이미 추가되었습니다", Toast.LENGTH_SHORT).show();
                            flag=false;
                        } else flag = true;
                        break;
                    case 102:
                        if (GlobalApplication.favor_102) {
                            Toast.makeText(User_TruckInfo.this, "이미 추가되었습니다", Toast.LENGTH_SHORT).show();
                            flag=false;
                        } else flag = true;
                        break;
                    case 103:
                        if (GlobalApplication.favor_103) {
                            Toast.makeText(User_TruckInfo.this, "이미 추가되었습니다", Toast.LENGTH_SHORT).show();
                            flag=false;
                        } else flag = true;
                        break;
                }
                if (flag) {
                    int numb = Integer.parseInt(tv_favor.getText().toString());
                    numb++;
                    tv_favor.setText(Integer.toString(numb));
                    db.execSQL("update foodtruck set favorites = " + numb + " where truck_id=" + truckdata.truck_id + ";");
                    db.close();
                    switch (truckdata.truck_id) {
                        case 101:
                            GlobalApplication.favor_101 = true;
                            break;
                        case 102:
                            GlobalApplication.favor_102 = true;
                            break;
                        case 103:
                            GlobalApplication.favor_103 = true;
                            break;
                    }
                }
            }
        });
    }
}
