package com.example.ckh.foodtruck;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Ckh on 2016-09-10.
 */
public class Seller_Login extends Activity{
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_main);

        Button sellerlogin = (Button) findViewById(R.id.sellermain_imgbtn_Login);
        sellerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seller_Login.this,Seller_TabFragment.class);
                startActivity(intent);
            }
        });
    }

}
