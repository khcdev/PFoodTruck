package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ckh.cstview.Seller_MenuItem;
import com.example.ckh.foodtruck.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;

/**
 * Created by Ckh on 2016-10-02.
 */
public class Seller_MenuAdd extends Activity{
    final int REQ_CODE_SELECT_IMAGE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_menu_item_info);
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
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.seller_menu_img);

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

        EditText title = (EditText) findViewById(R.id.seller_textinput_menu_item_title);
        EditText price = (EditText) findViewById(R.id.seller_textinput_menu_item_price);
        EditText from = (EditText) findViewById(R.id.seller_textinput_menu_item_from);
        EditText info = (EditText) findViewById(R.id.seller_textinput_menu_item_info);

        setResult(50);
        finish();
    }
}
