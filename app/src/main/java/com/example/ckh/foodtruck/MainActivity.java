package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener titlelayout_listener = new btnClickLister();

        Button imgbtn_seller = (Button) findViewById(R.id.titlelayout_start_seller);
        Button imgbtn_user = (Button)findViewById(R.id.titlelayout_start_user);
        imgbtn_seller.setOnClickListener(titlelayout_listener);
        //Test

    }

    class btnClickLister implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.titlelayout_start_seller:
                    Intent i = new Intent();
                    //startActivity();
                    break;
                case R.id.titlelayout_start_user:
                    break;
            }
        }
    }
}
