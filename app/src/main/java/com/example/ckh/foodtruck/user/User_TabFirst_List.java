package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ckh.cstview.UserTruckListviewAdapter;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-10-08.
 */
@SuppressLint("ValidFragment")
public class User_TabFirst_List extends Fragment {
    private ListView MenuList = null;
    private UserTruckListviewAdapter ListViewAdapter = null;
    View view;
    Context mContext;
    public User_TabFirst_List(Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(view == null) {
         view = inflater.inflate(R.layout.user_tab1_list, null);
        }
        MenuList=(ListView) view.findViewById(R.id.User_Truck_List);
        ListViewAdapter = new UserTruckListviewAdapter(getActivity());
        MenuList.setAdapter(ListViewAdapter);

        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.img_chungnyun_main,null),
                "청년반점",
                "레몬 크림 새우, 동파육 두가지 메뉴",
                "여의도 도깨비야시장");
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.img_gopizza_main,null),
                "Go Pizza",
                "다양한 종류의 화덕피자",
                "여의도 도깨비야시장"
                );
        ListViewAdapter.addItem(getResources().getDrawable(R.drawable.img_steakout_main,null),
                "Steak Out",
                "고급 스테이크를 저렴한 가격에!",
                "여의도 도깨비야시장");
        return view;
    }
}
