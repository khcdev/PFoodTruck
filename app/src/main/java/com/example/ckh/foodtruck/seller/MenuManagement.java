package com.example.ckh.foodtruck.seller;
//data/data/com.example.ckh.foodtruck/files/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.ckh.foodtruck.utility.MySharedPreferences;
import com.example.ckh.listView.SellerMenuListviewAdapter;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;

/**
 * Created by Ckh on 2016-10-02.
 * db가 오픈되면서 초기에 insert된 데이터들을 조건에 맞게
 */
public class MenuManagement extends Activity {
    DBSQLiteOpenHelper helper;
    SQLiteDatabase db;
    String imgpath = "data/data/com.example.ckh.foodtruck/files/";
    int truckcode =102;
    private ListView MenuList = null;
    private SellerMenuListviewAdapter ListViewAdapter = null;
    Bitmap bm;
    boolean isok=false;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_tab2_menumng);
        Intent intent = getIntent();
        truckcode = intent.getExtras().getInt("truckcode");
        Button adddetail = (Button) findViewById(R.id.seller_btn_menuadded);
        if(intent.getExtras().getBoolean("isSeller")) {
            AlertDialog.Builder noti = new AlertDialog.Builder(MenuManagement.this);
            noti.setMessage("메뉴 관리 화면입니다.\n User에게 보여지는 메뉴리스트들을 보여줍니다.\n" +
                    "add를 클릭하면 메뉴를 추가할 수 있습니다.\n 메뉴를 클릭하면 삭제할 수 있습니다.");
            noti.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            noti.show();
            adddetail.setVisibility(View.VISIBLE);
            adddetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuManagement.this, SellerMenuAdd.class);
                    startActivityForResult(intent, 100);
                }
            });

        }else {
            AlertDialog.Builder noti = new AlertDialog.Builder(MenuManagement.this);
            noti.setMessage("푸드트럭에서 판매하는 메뉴 리스트 입니다.\n ");
            noti.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            noti.show();
            adddetail.setVisibility(View.INVISIBLE);
        }

        MenuList=(ListView) findViewById(R.id.all_menu_list);
        ListViewAdapter = new SellerMenuListviewAdapter(this);
        MenuList.setAdapter(ListViewAdapter);

        helper = new DBSQLiteOpenHelper(
                MenuManagement.this,
                GlobalApplication.dbName,
                null,
                MySharedPreferences.getInstance().getAppDBVersion(MenuManagement.this)
        );
        accessDb();

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 50){
            this.recreate();
            ListViewAdapter.dataChange();
            Log.i("resultcode","결과 코드 확인");
            Log.i("ckhtag","확인");
        }
    }
    public void accessDb(){
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from menu where truck_id="+truckcode+";",null);
        c.getColumnIndex("column");
        while(c.moveToNext()){
            String imgcode = c.getString(6);
            int price = c.getInt(5);
            String origin = c.getString(4);
            try {
                bm = BitmapFactory.decodeFile(imgpath + imgcode+".png");
            }catch (Exception e){
                e.printStackTrace();
                Log.e("fileloadfailed","비트맵 이미지 불러오기 실패");
            }
            ListViewAdapter.addItem(bm,c.getString(2),Integer.toString(c.getInt(5)),c.getString(4),"");
        }
        ListViewAdapter.dataChange();
        c.close();
        db.close();
    }

}
