package com.example.ckh.foodtruck.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ckh.foodtruck.R;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by Ckh on 2016-09-25.
 */
public class UserTabFragment extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment);

        UserSectionPagerAdapter mSectionPagerAdapter;
        ViewPager mViewPager;

        mSectionPagerAdapter =  new UserSectionPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.user_viewpager);
        mViewPager.setAdapter(mSectionPagerAdapter);

        TabLayout UserTab = (TabLayout)findViewById(R.id.user_tabs);
        UserTab.setupWithViewPager(mViewPager);

    }
    public class UserSectionPagerAdapter extends FragmentPagerAdapter {
        Context mContext;
        public UserSectionPagerAdapter(Context context, FragmentManager fm){
            super(fm);
            mContext=context;
        }
        /*fragment page open*/
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new UserTabFirstList(mContext);
                case 1 :
                    return new UserTabSecMap(mContext);
                case 2:
                    return new UserTabTrisettings(mContext);
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
