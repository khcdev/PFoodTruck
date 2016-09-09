package com.example.ckh.foodtruck;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Ckh on 2016-09-08.
 */
public class User_Login extends Activity{
    @Override
    public void onCreate(Bundle b ){
        super.onCreate(b);
        setContentView(R.layout.user_main);

        ImageView imgbtn_NonAcc=(ImageView) findViewById(R.id.usermain_btn_NonAccLogin);
        ImageView imgbtn_kkoLogin=(ImageView) findViewById(R.id.usermain_btn_kkoLogin);
        ImageView imgbtn_fbLogin=(ImageView) findViewById(R.id.usermain_btn_fbLogin);

        class btnClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.usermain_btn_NonAccLogin :
                        break;
                    case R.id.usermain_btn_kkoLogin :
                        break;
                    case R.id.usermain_btn_fbLogin :
                        break;
                }
            }
        }
    }
}
