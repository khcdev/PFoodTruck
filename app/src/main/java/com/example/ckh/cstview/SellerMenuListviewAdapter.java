package com.example.ckh.cstview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;

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
    public Object getItem(int position) {
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

    /*public void dataChange() {
        allMenuAdapter.notifyDataSetChanged();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder allMenuHolder;
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

        if(allMenuData.MenuImage != null){
            allMenuHolder.foodImage.setVisibility(View.VISIBLE);
            allMenuHolder.foodImage.setImageBitmap(allMenuData.MenuImage);
        }
        else{
            allMenuHolder.foodImage.setVisibility(View.GONE);
        }

        allMenuHolder.foodName.setText(allMenuData.MenuTitle);
        allMenuHolder.foodPrice.setText(allMenuData.Price);
        allMenuHolder.foodOrigin.setText(allMenuData.Origin);

        return convertView;
    }
    private class ViewHolder{
        public ImageView foodImage;
        public TextView foodName;
        public TextView foodPrice;
        public TextView foodOrigin;
    }
}
