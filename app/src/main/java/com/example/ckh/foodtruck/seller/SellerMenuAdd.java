package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ckh on 2016-10-02.
 */
public class SellerMenuAdd extends Activity{

    boolean isCheck=false;
    ImageView image;
    Bitmap image_bitmap;
    DBSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    final int REQ_CODE_SELECT_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_menu_item_info);
        AlertDialog.Builder noti = new AlertDialog.Builder(SellerMenuAdd.this);
        noti.setMessage("입력폼을 작성한 후 승인 버튼을 누른뒤에 확인을 눌러주세요.");
        noti.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noti.show();
    }

    public void onClick_seller_Menu_imgadd(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

        TextView tv = (TextView) findViewById(R.id.seller_menu_tvVisibility);
        tv.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    image = (ImageView)findViewById(R.id.seller_menu_img);
                    image.setImageBitmap(image_bitmap);

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onClick_seller_Menu_addbtnclicked(View view) {
        if(isCheck) {
            String menuItem_price_detail;
            String menuItem_origin_detail;
            String menuItem_info_detail;
            String menuItem_title_detail;
            String convert_menunumber="";

            EditText title = (EditText) findViewById(R.id.seller_textinput_menu_item_title);
            EditText price = (EditText) findViewById(R.id.seller_textinput_menu_item_price);
            EditText origin = (EditText) findViewById(R.id.seller_textinput_menu_item_origin);
            EditText info = (EditText) findViewById(R.id.seller_textinput_menu_item_info);
            menuItem_title_detail = title.getText().toString();
            menuItem_price_detail = price.getText().toString();
            menuItem_origin_detail = origin.getText().toString();
            menuItem_info_detail = info.getText().toString();
            helper = new DBSQLiteOpenHelper(SellerMenuAdd.this,GlobalApplication.dbName,null,1);
            try{
                db=helper.getWritableDatabase();
            }catch(SQLiteException e){
                e.printStackTrace();
                Log.e("SQLite", "데이터베이스 생성/열기 실패");
                Toast.makeText(SellerMenuAdd.this, "DB opening failed", Toast.LENGTH_LONG).show();
            }

            Cursor c = db.rawQuery("select count(_id) from menu where truck_id=102",null);

            while(c.moveToNext()) {
                int menunum = c.getInt(0)+1;
                if(menunum<10) convert_menunumber="0"+menunum;
                else if(menunum<100)convert_menunumber=Integer.toString(menunum);
                Log.i("baeeee",convert_menunumber);
            }

            db.execSQL("insert into menu values(null,102, '"+menuItem_title_detail+"',"+0+", '"+menuItem_origin_detail+"',"+
                    Integer.parseInt(menuItem_price_detail)+", 'img_gopizza_m"+convert_menunumber+"'  );");

            saveFiletoInternalStorage(image_bitmap,"img_gopizza_m"+convert_menunumber+".png");
            AlertDialog.Builder successalert = new AlertDialog.Builder(SellerMenuAdd.this);
            successalert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            successalert.setMessage("메뉴가 추가 되었습니다!");
            setResult(50);
            finish();
        }else {
            AlertDialog.Builder alert = new AlertDialog.Builder(SellerMenuAdd.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
                }
            });
            alert.setMessage("메뉴가 승인 되지 않았습니다.");
            alert.show();
        }
    }

    public void onClick_seller_Menu_check(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(SellerMenuAdd.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        alert.setMessage("승인 신청 되었습니다.\n (승인까지 3초의 시간이 걸립니다.)");
        alert.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                isCheck=true;
            }
        }, 3000);
    }

    public void saveFiletoInternalStorage(Bitmap bm,String str){
        try{
            FileOutputStream fos = openFileOutput(str,0);
            bm.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            Log.e("file/IO",str+"in");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("fileIOerror","fileinerror");
        }

    }
}
