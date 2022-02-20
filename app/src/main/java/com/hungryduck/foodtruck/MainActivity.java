package com.hungryduck.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hungryduck.foodtruck.seller.SellerLogin;
import com.hungryduck.foodtruck.user.UserLogin;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener titleLayoutListener = new btnClickLister();
        ImageView imgBtn_seller = (ImageView) findViewById(R.id.titlelayout_start_seller);
        ImageView imgBtn_user = (ImageView)findViewById(R.id.titlelayout_start_user);
        imgBtn_seller.setOnClickListener(titleLayoutListener);
        imgBtn_user.setOnClickListener(titleLayoutListener);
    }

    class btnClickLister implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.titlelayout_start_seller:
                    Intent actSeller_Login = new Intent(MainActivity.this,SellerLogin.class);
                    startActivity(actSeller_Login);
                    break;
                case R.id.titlelayout_start_user:
                    Intent actUser_Login = new Intent(MainActivity.this,UserLogin.class);
                    startActivity(actUser_Login);
                    break;
            }
        }
    }
}
