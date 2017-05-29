package com.example.ckh.listView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.ViewDTO.FavorListViewAdapterDTO;
import com.example.ckh.ViewDTO.FavorTruckDTO;

/**
 * Created by Ckh on 2016-10-30.
 */
public class FavorListViewAdapter extends BaseAdapter {
    private Context mContext = null;

    public FavorListViewAdapter(Context allMenuContext) {
        super();
        this.mContext = allMenuContext;
    }

    @Override
    public int getCount() {
        return GlobalApplication.favortruckList.size();
    }

    @Override
    public Object getItem(int position) {
        return GlobalApplication.favortruckList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Drawable truckimg, String name, int truckid) {
        FavorTruckDTO datainfo = new FavorTruckDTO();
        datainfo.setTruckImg(truckimg);
        datainfo.setTruck_id(truckid);
        datainfo.setTruckName(name);

        GlobalApplication.favortruckList.add(datainfo);

    }

    public void remonve(int position) {
        GlobalApplication.favortruckList.remove(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavorListViewAdapterDTO holder;
        if (convertView == null) {
            holder = new FavorListViewAdapterDTO();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favortrucklistform, null);

            holder.setImg((ImageView) convertView.findViewById(R.id.favor_truck_img));
            holder.setName((TextView) convertView.findViewById(R.id.favor_truck_name));
            holder.setLayout((LinearLayout) convertView.findViewById(R.id.favor_truck_container));
            holder.getLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            convertView.setTag(holder);
        } else {
            holder = (FavorListViewAdapterDTO) convertView.getTag();
        }
        FavorTruckDTO data = GlobalApplication.favortruckList.get(position);
        if (data.getTruckImg() != null) {
            holder.getImg().setVisibility(View.VISIBLE);
            holder.getImg().setImageDrawable(data.getTruckImg());
        } else {
            holder.getImg().setVisibility(View.GONE);
        }
        holder.getName().setText(data.getTruckName());
        return convertView;
    }
//
//    FavorListViewAdapterDTO 로 대체
//    private class ViewHolder{
//        public ImageView img;
//        public TextView name;
//        public LinearLayout layout;
//    }
}
