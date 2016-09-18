package com.example.ckh.foodtruck;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

/**
 * Created by Ckh on 2016-09-18.
 */
public class User_tmploginaccess extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginsuccess);

        TextView txtview = (TextView) findViewById(R.id.user_data_name);
        if(GlobalApplication.User_info_name==null)GlobalApplication.User_info_name="손님";
        txtview.setText("이름 : "+ GlobalApplication.User_info_name);
    }
}
