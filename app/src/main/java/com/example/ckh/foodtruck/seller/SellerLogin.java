package com.example.ckh.foodtruck.seller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;


/**
 * Created by Ckh on 2016-09-10.
 */
public class SellerLogin extends Activity{

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_main);

        ImageView sellerlogin = (ImageView) findViewById(R.id.sellermain_imgbtn_Login);
        final EditText i_login = (EditText) findViewById(R.id.seller_lgn_edit_text_id);
        final EditText i_password =(EditText) findViewById(R.id.seller_lgn_edit_text_password);
        i_login.setText("gopizza");
        i_password.setText("0000");

        //sqlite를 활용하여 회원연동 수정
        sellerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean id_check=false;
                boolean pass_check=false;
                if(i_login.getText().toString().equals("gopizza")) id_check=true;
                if(i_password.getText().toString().equals("0000")) pass_check=true;
                Log.i("logindata",i_login.getText().toString()+id_check+","+i_password.getText().toString()+pass_check);
                if(id_check&&pass_check) {
                    Intent intent = new Intent(SellerLogin.this, SellerTabFragment.class);
                    startActivity(intent);
                    GlobalApplication.seller = true;
                    GlobalApplication.fbuser=false;
                    GlobalApplication.kkouser=false;
                    finish();
                }
                else Toast.makeText(SellerLogin.this,"아이디 혹은 패스워드가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }
        });


    }

}
