package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.ckh.cstview.SellerReviewListviewAdapter;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

/**
 * Created by Ckh on 2016-09-25.
 */
public class ReviewMore extends Activity {
    SQLiteDatabase db;
    DBSQLiteOpenHelper helper;
    Button reviewadd;
    ListView listview;
    SellerReviewListviewAdapter adapter;
    int truckid=102;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_reviewmore);
        final Intent intent = getIntent();
        reviewadd =(Button) findViewById(R.id.reviewadd);
        if(intent.getExtras().getInt("Req")==0) {
            reviewadd.setVisibility(View.INVISIBLE);

        }else {
            reviewadd.setVisibility(View.VISIBLE);
            truckid=intent.getExtras().getInt("truckcode");
            reviewadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //리뷰 추가 액티비티
                }
            });

        }
        listview = (ListView) findViewById(R.id.reviewList);
        adapter = new SellerReviewListviewAdapter(ReviewMore.this);
        listview.setAdapter(adapter);

        helper = new DBSQLiteOpenHelper(ReviewMore.this,
                GlobalApplication.dbName,
                null,
                1
        );
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select writer,date,contents from review where truck_id = "+truckid+";",null);
        while(c.moveToNext()){
            c.getString(0); //writer
            adapter.addItem(c.getString(0),c.getString(1),0.1,c.getString(2));
        }
    }


}
