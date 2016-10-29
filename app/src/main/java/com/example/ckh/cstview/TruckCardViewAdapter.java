package com.example.ckh.cstview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.user.User_TruckInfo;

import java.util.ArrayList;
/**
 * Created by Ckh on 2016-10-28.
 */

public class TruckCardViewAdapter extends RecyclerView.Adapter<TruckCardViewAdapter.ViewHolder> {
    Context mContext;
    ArrayList<TruckItem> truckdata;

    public TruckCardViewAdapter(Context context, ArrayList<TruckItem> List){
        this.mContext=context;
        this.truckdata = List;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_truck_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TruckItem Data = truckdata.get(position);
        final int pos = position;
        String titledata = Data.truckname;
        String notistrdata =Data.truck_noti;
        if(titledata.length()>18) {
            titledata = titledata.substring(0, 18) + "...";
        }
        holder.truck_title.setText(titledata);
        if(notistrdata.length()>50){
            notistrdata = notistrdata.substring(0,50)+"...";
        }
        holder.truck_notiuser.setText(notistrdata);
        holder.truck_favornum.setText(Integer.toString(Data.truckfavor));
        holder.truck_image.setBackground(new BitmapDrawable(Data.truckimg));
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 이벤트
                Intent intent = new Intent(mContext,User_TruckInfo.class);
                intent.putExtra("truckname",Data.truckname);
                intent.putExtra("truckid",Data.truck_id);
                intent.putExtra("truckfavor",Data.truckfavor);
                intent.putExtra("truckscore",Data.truckscore);
                intent.putExtra("trucknoti",Data.truck_noti);
                intent.putExtra("imgcode",Data.imgcode);
                Log.i("ckh/intent",Data.truckname+" "+Data.truck_noti+" "+Data.truck_noti);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return truckdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView truck_title;
        TextView truck_notiuser;
        TextView truck_favornum;
        ImageView truck_image;
        CardView cv;

        public ViewHolder(View v) {
            super(v);
            truck_image = (ImageView) v.findViewById(R.id.user_truckinfo_truckimage);
            truck_title = (TextView) v.findViewById(R.id.user_truckinfo_trucktitle);
            truck_notiuser = (TextView) v.findViewById(R.id.user_truckinfo_notitouser);
            truck_favornum = (TextView) v.findViewById(R.id.user_truckinfo_favornumber);
            cv = (CardView) v.findViewById(R.id.cv);
        }
    }
}
