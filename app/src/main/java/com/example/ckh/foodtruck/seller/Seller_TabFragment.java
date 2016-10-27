package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import com.example.ckh.foodtruck.R;

/**
 * Created by Ckh on 2016-09-18.
 */
@SuppressLint("DefaultLocale")
public class Seller_TabFragment extends FragmentActivity {
    SellerSectionPagerAdapter mSectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_fragment);

        mSectionPagerAdapter =new SellerSectionPagerAdapter(getApplicationContext(),getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.seller_viewpager);
        mViewPager.setAdapter(mSectionPagerAdapter);


        TabLayout SellerTab = (TabLayout)findViewById(R.id.seller_tabs);
        SellerTab.setupWithViewPager(mViewPager);

    }
    @SuppressLint("DefaultLocale")
    public class SellerSectionPagerAdapter extends FragmentPagerAdapter{
        Context mContext;
        public SellerSectionPagerAdapter(Context context,FragmentManager fm){
            super(fm);
            mContext=context;
        }
        /*fragment page open*/
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new Seller_TabFirst_Sales(mContext);
                case 1 :
                    return new Seller_TabSec_Store(mContext);
                case 2 :
                    return new Seller_TabTri_News(mContext);
                case 3 :
                    return new Seller_TabFour_Settings(mContext);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
        /*fragment title get*/
        @SuppressLint("DefaultLocale")
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return mContext.getString(R.string.str_seller_sales).toUpperCase();
                case 1:
                    return mContext.getString(R.string.str_seller_storemng).toUpperCase();
                case 2:
                    return mContext.getString(R.string.str_seller_news).toUpperCase();
                case 3:
                    return mContext.getString(R.string.str_seller_settings).toUpperCase();
            }
            return null;
        }
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        //getMenuInflater().inflate(R.);
        return true;
    }*/


}

