package com.hungryduck.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hungryduck.listview.FavorListViewAdapter;
import com.hungryduck.foodtruck.utility.GlobalApplication;
import com.hungryduck.foodtruck.R;

/**
 * Created by Ckh on 2016-10-28.
 */
@SuppressLint("ValidFragment")
public class UserTabTrisettings extends Fragment {
    private Context mContext;
    private View view;

    public UserTabTrisettings(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.user_tab3_settings, null);
        }

        TextView tv1;
        ListView favorlist;
        FavorListViewAdapter adapter;

        tv1 = (TextView) view.findViewById(R.id.user_id);
        tv1.setText(GlobalApplication.User_info_name);
        favorlist = (ListView) view.findViewById(R.id.favorlist);
        adapter = new FavorListViewAdapter(getActivity());
        favorlist.setAdapter(adapter);

        if (GlobalApplication.favor_101) {
            boolean isin = false;
            for (int i = 0; i < GlobalApplication.favortruckList.size(); i++) {
                if (GlobalApplication.favortruckList.get(i).getTruck_id() == 101) isin = true;
            }
            if (!isin)
                adapter.addItem(getResources().getDrawable(R.drawable.img_steakout_main), "SteakOut", 101);

        }
        if (GlobalApplication.favor_102) {
            boolean isin = false;
            for (int i = 0; i < GlobalApplication.favortruckList.size(); i++) {
                if (GlobalApplication.favortruckList.get(i).getTruck_id() == 102) isin = true;
            }
            if (!isin)
                adapter.addItem(getResources().getDrawable(R.drawable.img_gopizza_main), "GoPizza", 102);
        }
        if (GlobalApplication.favor_103) {
            boolean isin = false;
            for (int i = 0; i < GlobalApplication.favortruckList.size(); i++) {
                if (GlobalApplication.favortruckList.get(i).getTruck_id() == 103) isin = true;
            }
            if (!isin)
                adapter.addItem(getResources().getDrawable(R.drawable.img_chungnyun_main), "청년반점", 103);
        }

        return view;
    }
}
