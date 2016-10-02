package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.ckh.cstview.SellerMenuListviewAdapter;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-02.
 */
public class MenuManagement extends Activity {

    private ListView MenuList = null;
    private SellerMenuListviewAdapter ListViewAdapter = null;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_tab2_menumng);

        MenuList=(ListView) findViewById(R.id.all_menu_list);
        ListViewAdapter = new SellerMenuListviewAdapter(this);
        MenuList.setAdapter(ListViewAdapter);

        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.cvt_truck1_menu1,null),
                "미니스테이크",
                "8000원",
                "호주산",
                "호주산 청정우를 싼가격에 손님께 제공합니다.");
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.cvt_truck2_menu1,null),
                "브리또",
                "6000원",
                "국산",
                "맛있는 브리또");
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.cvt_truck3_menu3,null),
                "모히또",
                "6000원",
                "필리핀산",
                "모히또가서 몰디브 한잔 ");
    }


}
