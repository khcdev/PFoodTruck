package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.ckh.foodtruck.utility.MySharedPreferences;
import com.example.ckh.listView.SellerReviewListViewAdapter;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.user.UserReviewAdding;

/**
 * Created by Ckh on 2016-09-25.
 */
public class ReviewMore extends Activity {

    private SellerReviewListViewAdapter adapter;

    //FIXME : GlobalApplication static 트럭 데이터 해결
    int truckid=102;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_reviewmore);
        Button reviewAdd;
        ListView listView;
        final Intent intent = getIntent();
        reviewAdd =(Button) findViewById(R.id.reviewadd);
        if(intent.getExtras().getInt("Req")==0) {
            reviewAdd.setVisibility(View.INVISIBLE);

        }else {
            reviewAdd.setVisibility(View.VISIBLE);
            truckid=intent.getExtras().getInt("truckcode");
            reviewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(GlobalApplication.User_info_name==null) {
                        Toast.makeText(ReviewMore.this,"로그인 후 이용해 주세요",Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(ReviewMore.this, UserReviewAdding.class);
                        intent.putExtra("truckcode", truckid);
                        startActivityForResult(intent,10);
                    }
                }
            });

        }
        listView = (ListView) findViewById(R.id.reviewList);
        adapter = new SellerReviewListViewAdapter(ReviewMore.this);
        listView.setAdapter(adapter);

        DBSQLiteOpenHelper helper = new DBSQLiteOpenHelper(ReviewMore.this,
                GlobalApplication.dbName,
                null,
                MySharedPreferences.getInstance().getAppDBVersion(ReviewMore.this)
        );
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select writer,date,contents,score from review where truck_id = "+truckid+";",null);
        while(c.moveToNext()){
            c.getString(0); //writer
            adapter.addItem(c.getString(0),c.getString(1),c.getInt(3),c.getString(2));
        }
        c.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==1){
            this.recreate();
            adapter.notifyDataSetChanged();
        }
    }
}
