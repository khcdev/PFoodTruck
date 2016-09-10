package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Ckh on 2016-09-10.
 * 어플리케이션의 로딩 화면 Splash
 */
public class Splash extends Activity {

    //로딩 화면이 떠있는 시간(밀리초단위)
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    /**임의 시간이 아닌 실제 데이터 로딩시간에 기반하도록 추후 변경*/
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.splash_loading);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료한다.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면(splash) 끝*/
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
