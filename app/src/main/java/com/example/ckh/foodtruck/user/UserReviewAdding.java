package com.example.ckh.foodtruck.user;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.utility.MySharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ckh on 2016-10-30.
 */
public class UserReviewAdding extends Activity {
    private EditText input1;
    private RatingBar rating;
    private SQLiteDatabase db;
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.user_review_add);

        Button btn;
        DBSQLiteOpenHelper helper;

        Intent intent =getIntent();
        final int truckNumber = intent.getExtras().getInt("truckcode");
        input1 = (EditText) findViewById(R.id.user_review_add_edittext_one);
        rating = (RatingBar) findViewById(R.id.user_review_add_ratingbar);
        btn =(Button) findViewById(R.id.user_review_add_button_one);

        helper = new DBSQLiteOpenHelper(
                this,
                GlobalApplication.dbName,
                null,
                MySharedPreferences.getInstance().getAppDBVersion(UserReviewAdding.this));
        db = helper.getWritableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                int score;
                msg=input1.getText().toString();
                score = (int)rating.getRating();
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String convertData =  CurDateFormat.format(date);
                db.execSQL("insert into review values (null,"+truckNumber+",'"+GlobalApplication.User_info_name+"','"+msg+"',"+score+",'"+convertData+"');");
                db.close();
                Toast.makeText(UserReviewAdding.this,"리뷰가 추가되었습니다!",Toast.LENGTH_LONG).show();
                setResult(1);
                finish();
            }
        });

    }
}
