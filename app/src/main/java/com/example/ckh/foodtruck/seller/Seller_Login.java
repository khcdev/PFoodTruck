package com.example.ckh.foodtruck.seller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.ckh.foodtruck.R;


/**
 * Created by Ckh on 2016-09-10.
 */
public class Seller_Login extends Activity{
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_main);

        ImageView sellerlogin = (ImageView) findViewById(R.id.sellermain_imgbtn_Login);
        final EditText i_login = (EditText) findViewById(R.id.seller_lgn_edit_text_id);
        final EditText i_password =(EditText) findViewById(R.id.seller_lgn_edit_text_password);

        sellerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Seller_Login.this, Seller_TabFragment.class);
                    startActivity(intent);
                finish();
            }
        });
    }

}
