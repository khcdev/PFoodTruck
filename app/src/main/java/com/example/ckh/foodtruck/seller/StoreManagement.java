package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

/**
 * Created by Ckh on 2016-10-27.
 */
public class StoreManagement extends Activity {
    /*DBSQLiteOpenHelper helper;
    SQLiteDatabase db;*/
    String value_intro;
    String value_noti;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_storeinfo);

        final TextView tv1 = (TextView) findViewById(R.id.seller_textinput_store_introstore);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_intro = new AlertDialog.Builder(StoreManagement.this);
                alert_intro.setTitle("매장소개");
                final EditText input_intro = new EditText(StoreManagement.this);
                alert_intro.setView(input_intro);
                alert_intro.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        value_intro = input_intro.getText().toString();
                        tv1.setText("매장소개 : "+value_intro);
                        dialog.dismiss();
                        // Do something with value!
                    }
                });
                alert_intro.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                                dialog.dismiss();
                            }
                        });

                alert_intro.show();

            }

        });
        final TextView tv2 =(TextView) findViewById(R.id.seller_textinput_store_notification);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_noti = new AlertDialog.Builder(StoreManagement.this);
                alert_noti.setTitle("한마디 알림");
                final EditText input_noti = new EditText(StoreManagement.this);
                alert_noti.setView(input_noti);
                alert_noti.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value_noti = input_noti.getText().toString();
                        tv1.setText("한마디 : "+value_noti);
                        dialog.dismiss();
                        // Do something with value!
                    }
                });
                alert_noti.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                                dialog.dismiss();
                            }
                        });
                alert_noti.show();

            }

        });





        /*helper = new DBSQLiteOpenHelper(StoreManagement.this, GlobalApplication.dbName, null,1);
        db = helper.getWritableDatabase();
        */
    }
}
