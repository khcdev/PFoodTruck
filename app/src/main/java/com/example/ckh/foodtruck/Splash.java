package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * Created by Ckh on 2016-09-10.
 * 어플리케이션의 로딩 화면 Splash
 *
 * 초기 데이터 세팅 진행
 * - db오픈 및 초기 데이터 세팅 값 저장
 * - drawable resource 앱 설치후 내부 저장소에 따로 저장
 */

public class Splash extends Activity {
    DBSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    String tag = "SQLite";
    ProgressBar progressBar;

    //로딩 화면이 떠있는 시간(밀리초단위)
    private final int SPLASH_DISPLAY_LENGTH = 7000;
    /**임의 시간이 아닌 실제 데이터 로딩시간에 기반하도록 추후 변경*/

    @Override
    public void onCreate(Bundle b){
        SharedPreferences pref;
        super.onCreate(b);
        setContentView(R.layout.splash_loading);
        progressBar = (ProgressBar)findViewById(R.id.pgbar);

        Toast.makeText(Splash.this,"데이터를 읽어오는 중입니다.",Toast.LENGTH_LONG).show();

        pref=getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(!pref.getBoolean("isFirst",false)){
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("isFirst",true);
            edit.commit();

            saveFiletoInternalStorage(R.drawable.img_chungnyun_m01,"img_chungnyun_m01.png");
            saveFiletoInternalStorage(R.drawable.img_chungnyun_m02,"img_chungnyun_m02.png");
            saveFiletoInternalStorage(R.drawable.img_chungnyun_main,"img_chungnyun_main.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m01,"img_gopizza_m01.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m02,"img_gopizza_m02.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m03,"img_gopizza_m03.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_main,"img_gopizza_main.png");
            saveFiletoInternalStorage(R.drawable.img_steakout_m01,"img_steakout_m01.png");
            saveFiletoInternalStorage(R.drawable.img_steakout_main,"img_steakout_main.png");
        }
        new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    helper = new DBSQLiteOpenHelper(
                            Splash.this,
                            GlobalApplication.dbName,
                            null,
                            1
                    );
                    try{
                        db = helper.getWritableDatabase();
                    }catch (SQLiteException e) {
                        e.printStackTrace();
                        Log.e(tag, "데이터베이스 생성/열기 실패");
                        Toast.makeText(Splash.this, "DB opening failed", Toast.LENGTH_LONG).show();
                    }
                    GlobalApplication.truckintro = new HashMap<String, String>();
                    GlobalApplication.truckintro.put("101","합리적인 가격에 맛있는 스테이크를 제공합니다.");
                    GlobalApplication.truckintro.put("102","화덕 피자 전문점");
                    GlobalApplication.truckintro.put("103","최고 인기메뉴 레몬크림새우, 늦으면 즐기실수 없는 동파육!");

                    GlobalApplication.trucknoti =new HashMap<String, String>();
                    GlobalApplication.trucknoti.put("101","11월 8일 4시~8시 왕십리역 akplaza 뒤편(먹자골목)");
                    GlobalApplication.trucknoti.put("102","11월 3일 세종대학교 정문 11시~3시");
                    GlobalApplication.trucknoti.put("103","매주 금요일 토요일 밤도깨비 야시장에서 만나요 :)");
                    /* 메뉴액티비티를 실행하고 로딩화면(splash) 끝*/
                    db.close();

                    //Bitmap
                    //Bitmap bm
                    Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();}
            }, SPLASH_DISPLAY_LENGTH);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료한다.*/

    }
    public void saveFiletoInternalStorage(int rvalue,String str){
        try{
            FileOutputStream fos = openFileOutput(str,0);
            Bitmap bm = BitmapFactory.decodeResource(getResources(),rvalue);
            bm.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            Log.e("fileIO",str+"in");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("fileIOerror","fileinerror");
        }

    }
}
