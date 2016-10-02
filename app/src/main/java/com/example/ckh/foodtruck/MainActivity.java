package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener titlelayout_listener = new btnClickLister();

        ImageView imgbtn_seller = (ImageView) findViewById(R.id.titlelayout_start_seller);
        ImageView imgbtn_user = (ImageView)findViewById(R.id.titlelayout_start_user);
        imgbtn_seller.setOnClickListener(titlelayout_listener);
        imgbtn_user.setOnClickListener(titlelayout_listener);
    }

    class btnClickLister implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.titlelayout_start_seller:
                    Intent actSeller_Login = new Intent(MainActivity.this,Seller_Login.class);
                    startActivity(actSeller_Login);
                    break;
                case R.id.titlelayout_start_user:
                    Intent actUser_Login = new Intent(MainActivity.this,User_Login.class);
                    startActivity(actUser_Login);
                    break;
            }
        }
    }
}
