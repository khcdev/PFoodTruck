package com.example.ckh.cstview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ckh.foodtruck.GlobalApplication;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-30.
 */
public class favorListViewAdapter extends BaseAdapter {
    private Context mContext = null;

    public favorListViewAdapter(Context allMenuContext){
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
    public void addItem(Drawable truckimg, String name, int truckid){
        favorTruck datainfo = new favorTruck();
        datainfo.truckImg = truckimg;
        datainfo.truck_id=truckid;
        datainfo.truckName=name;

        GlobalApplication.favortruckList.add(datainfo);

    }
    public void remonve(int position){
        GlobalApplication.favortruckList.remove(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favortrucklistform,null);

            holder.img = (ImageView) convertView.findViewById(R.id.favor_truck_img);
            holder.name =(TextView) convertView.findViewById(R.id.favor_truck_name);
            holder.layout=(LinearLayout) convertView.findViewById(R.id.favor_truck_container);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        favorTruck data = GlobalApplication.favortruckList.get(position);
        if(data.truckImg!=null){
            holder.img.setVisibility(View.VISIBLE);
            holder.img.setImageDrawable(data.truckImg);
        }else{
            holder.img.setVisibility(View.GONE);
        }
        holder.name.setText(data.truckName);
        return convertView;
    }
    private class ViewHolder{
        public ImageView img;
        public TextView name;
        public LinearLayout layout;
    }
}
