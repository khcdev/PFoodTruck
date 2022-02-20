package com.example.ckh.listView;


import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.viewdto.MenuViewHolderDTO;
import com.example.ckh.viewdto.SellerMenuItemDTO;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-02.
 */
public class SellerMenuListviewAdapter extends BaseAdapter {
    private Context allMenuContext = null;
    private ArrayList<SellerMenuItemDTO> allMenuListData = new ArrayList<>();

    public SellerMenuListviewAdapter(Context allMenuContext) {
        super();
        this.allMenuContext = allMenuContext;
    }

    @Override
    public int getCount() {
        return allMenuListData.size();
    }

    @Override
    public SellerMenuItemDTO getItem(int position) {
        return allMenuListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Bitmap foodImage, String foodName, String foodPrice, String foodOrigin, String foodInfo) {
        SellerMenuItemDTO addInfo = new SellerMenuItemDTO();
        addInfo.setMenuImage(foodImage);
        addInfo.setMenuTitle(foodName);
        addInfo.setPrice(foodPrice);
        addInfo.setOrigin(foodOrigin);
        addInfo.setInfo(foodInfo);

        allMenuListData.add(addInfo);

    }

    public void remove(int position) {
        allMenuListData.remove(position);
    }

    public void dataChange() {
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MenuViewHolderDTO MenuHolderDTO;
        final String menuName;
        if (convertView == null) {
            MenuHolderDTO = new MenuViewHolderDTO();

            LayoutInflater inflater = (LayoutInflater) allMenuContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.seller_menu_added_layout, null);

            MenuHolderDTO.setFoodImage((ImageView) convertView.findViewById(R.id.all_menu_image));
            MenuHolderDTO.setFoodName((TextView) convertView.findViewById(R.id.all_menu_foodname));
            MenuHolderDTO.setFoodPrice((TextView) convertView.findViewById(R.id.all_menu_price));
            MenuHolderDTO.setFoodOrigin((TextView) convertView.findViewById(R.id.all_menu_origin));
            convertView.setTag(MenuHolderDTO);
        } else {
            MenuHolderDTO = (MenuViewHolderDTO) convertView.getTag();
        }

        SellerMenuItemDTO allMenuData = allMenuListData.get(position);
        menuName = allMenuData.getMenuTitle();
        if (allMenuData.getMenuImage() != null) {
            MenuHolderDTO.getFoodImage().setVisibility(View.VISIBLE);
            MenuHolderDTO.getFoodImage().setImageBitmap(allMenuData.getMenuImage());
        } else {
            MenuHolderDTO.getFoodImage().setVisibility(View.GONE);
        }

        MenuHolderDTO.getFoodName().setText(allMenuData.getMenuTitle());
        MenuHolderDTO.getFoodPrice().setText(allMenuData.getPrice() + "원");
        MenuHolderDTO.getFoodOrigin().setText(allMenuData.getOrigin());

        if (GlobalApplication.seller) {
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
                            helper = new DBSQLiteOpenHelper(allMenuContext, GlobalApplication.dbName, null, 1);
                            db = helper.getWritableDatabase();
                            db.execSQL("delete from menu where menu_name='" + menuName + "';");
                            Log.i("ckhtag", menuName);
                            remove(position);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    not.show();
                }
            });
        }
        return convertView;
    }
//    MenuViewHolderDTO 로 대체
//    private class ViewHolder {
//        public ImageView foodImage;
//        public TextView foodName;
//        public TextView foodPrice;
//        public TextView foodOrigin;
//    }
}
