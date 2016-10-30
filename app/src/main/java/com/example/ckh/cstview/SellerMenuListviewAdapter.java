package com.example.ckh.cstview;


import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-02.
 */
public class SellerMenuListviewAdapter extends BaseAdapter {
    private Context allMenuContext = null;
    private ArrayList<Seller_MenuItem> allMenuListData = new ArrayList<Seller_MenuItem>();

    public SellerMenuListviewAdapter(Context allMenuContext){
        super();
        this.allMenuContext = allMenuContext;
    }

    @Override
    public int getCount() {
        return allMenuListData.size();
    }

    @Override
    public Seller_MenuItem getItem(int position) {
        return allMenuListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Bitmap foodImage, String foodName, String foodPrice, String foodOrigin, String foodInfo){
        Seller_MenuItem addInfo = new Seller_MenuItem();
        addInfo.MenuImage = foodImage;
        addInfo.MenuTitle = foodName;
        addInfo.Price = foodPrice;
        addInfo.Origin = foodOrigin;
        addInfo.Info = foodInfo;

        allMenuListData.add(addInfo);

    }

    public void remove(int position){
        allMenuListData.remove(position);
    }

    public void dataChange() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder allMenuHolder;
        final String menuName;
        if(convertView == null){
            allMenuHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)allMenuContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.seller_menu_added_layout,null);

            allMenuHolder.foodImage = (ImageView)convertView.findViewById(R.id.all_menu_image);
            allMenuHolder.foodName = (TextView)convertView.findViewById(R.id.all_menu_foodname);
            allMenuHolder.foodPrice = (TextView)convertView.findViewById(R.id.all_menu_price);
            allMenuHolder.foodOrigin = (TextView)convertView.findViewById(R.id.all_menu_origin);
            convertView.setTag(allMenuHolder);
        }
        else{
            allMenuHolder = (ViewHolder)convertView.getTag();
        }

        Seller_MenuItem allMenuData = allMenuListData.get(position);
        menuName = allMenuData.MenuTitle;
        if(allMenuData.MenuImage != null){
            allMenuHolder.foodImage.setVisibility(View.VISIBLE);
            allMenuHolder.foodImage.setImageBitmap(allMenuData.MenuImage);
        }
        else{
            allMenuHolder.foodImage.setVisibility(View.GONE);
        }

        allMenuHolder.foodName.setText(allMenuData.MenuTitle);
        allMenuHolder.foodPrice.setText(allMenuData.Price+"원");
        allMenuHolder.foodOrigin.setText(allMenuData.Origin);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder not = new AlertDialog.Builder(allMenuContext);
                not.setMessage("메뉴를 삭제하시겠습니까?");
                not.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                not.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db;
                        DBSQLiteOpenHelper helper;
                        helper = new DBSQLiteOpenHelper(allMenuContext, GlobalApplication.dbName,null,1);
                        db = helper.getWritableDatabase();
                        db.execSQL("delete from menu where menu_name='"+menuName+"';");
                        Log.i("ckhtag",menuName);
                        remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                not.show();
            }
        });
        return convertView;
    }
    private class ViewHolder{
        public ImageView foodImage;
        public TextView foodName;
        public TextView foodPrice;
        public TextView foodOrigin;
    }
}
