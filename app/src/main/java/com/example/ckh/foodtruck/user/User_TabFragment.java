package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-09-25.
 */
public class User_TabFragment extends FragmentActivity {
    UserSectionPagerAdapter mSectionPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment);

        mSectionPagerAdapter =  new UserSectionPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.user_viewpager);
        mViewPager.setAdapter(mSectionPagerAdapter);

        TabLayout UserTab = (TabLayout)findViewById(R.id.user_tabs);
        UserTab.setupWithViewPager(mViewPager);

    }
    public class UserSectionPagerAdapter extends FragmentPagerAdapter {
        Context mContext;
        public UserSectionPagerAdapter(Context context,FragmentManager fm){
            super(fm);
            mContext=context;
        }
        /*fragment page open*/
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new User_TabFirst_List(mContext);
                case 1 :
                    return new User_TabSec_Map(mContext);
                case 2:
                    return null;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
        /*fragment title get*/
        @SuppressLint("DefaultLocale")
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return mContext.getString(R.string.str_user_list).toUpperCase();
                case 1:
                    return mContext.getString(R.string.str_user_map).toUpperCase();
                case 2:
                    return mContext.getString(R.string.str_user_setting).toUpperCase();
            }
            return null;
        }
    }
}
