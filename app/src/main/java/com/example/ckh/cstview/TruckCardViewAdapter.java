package com.example.ckh.cstview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ckh on 2016-10-28.
 */
public class TruckCardViewAdapter extends RecyclerView.Adapter<TruckCardViewAdapter.ViewHolder> {
    Context mContext;
    ArrayList<HashMap<String,String>> DataList;

    public TruckCardViewAdapter(Context context, ArrayList<HashMap<String,String>> List){
        this.mContext=context;
        this.DataList = List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_truck_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String,String> item = DataList.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView truck_title;
        TextView truck_notiuser;
        TextView truck_favornum;
        CardView cv;

        public ViewHolder(View v) {
            super(v);
            truck_title = (TextView) v.findViewById(R.id.tv_title);
            truck_notiuser = (TextView) v.findViewById(R.id.user_truckinfo_notitouser);
            truck_favornum = (TextView) v.findViewById(R.id.user_truckinfo_trucktitle);
            cv = (CardView) v.findViewById(R.id.cv);
        }
    }
}
