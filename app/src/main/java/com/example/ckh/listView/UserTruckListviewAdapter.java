package com.example.ckh.listView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ckh.foodtruck.R;
import com.example.ckh.viewdto.TruckDataDTO;
import com.example.ckh.viewdto.User_TruckItemDTO;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-02.
 */
public class UserTruckListviewAdapter extends BaseAdapter {
    private Context allMenuContext = null;
    private ArrayList<User_TruckItemDTO> allMenuListData = new ArrayList<User_TruckItemDTO>();

    public UserTruckListviewAdapter(Context allMenuContext) {
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

    public void addItem(Drawable truckimg, String trucktitle, String truckInfo, String truckLoc) {
        User_TruckItemDTO addInfo = new User_TruckItemDTO();
        addInfo.setTruckImg(truckimg);
        addInfo.setTruckTitle(trucktitle);
        addInfo.setTruckInfo(truckInfo);
        addInfo.setTruckLoc(truckLoc);
        allMenuListData.add(addInfo);
    }

    public void remove(int position) {
        allMenuListData.remove(position);
    }

    /*public void dataChange() {
        allMenuAdapter.notifyDataSetChanged();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TruckDataDTO TruckData;
        if (convertView == null) {
            TruckData = new TruckDataDTO();

            LayoutInflater inflater = (LayoutInflater) allMenuContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.seller_menu_added_layout, null);

            //리스트에 들어갈 데이터
            TruckData.setTruckimg((ImageView) convertView.findViewById(R.id.all_menu_image));
            TruckData.setTruckTitle((TextView) convertView.findViewById(R.id.all_menu_foodname));
            TruckData.setTruckInfo((TextView) convertView.findViewById(R.id.all_menu_price));
            TruckData.setTruckLoc((TextView) convertView.findViewById(R.id.all_menu_origin));


            convertView.setTag(TruckData);
        } else {
            TruckData = (TruckDataDTO) convertView.getTag();
        }

        User_TruckItemDTO allMenuData = allMenuListData.get(position);
        if (allMenuData.getTruckImg() != null) {
            TruckData.getTruckimg().setVisibility(View.VISIBLE);
            TruckData.getTruckimg().setImageDrawable(allMenuData.getTruckImg());
        } else {
            TruckData.getTruckimg().setVisibility(View.GONE);
        }

        TruckData.getTruckTitle().setText(allMenuData.getTruckTitle());
        TruckData.getTruckInfo().setText(allMenuData.getTruckInfo());
        TruckData.getTruckLoc().setText(allMenuData.getTruckLoc());


        return convertView;
    }

//      TruckDataDTO로 재정의
//    private class ViewHolder {
//        public ImageView Truckimg;
//        public TextView TruckTitle;
//        public TextView TruckInfo;
//        public TextView TruckLoc;
//    }

}
