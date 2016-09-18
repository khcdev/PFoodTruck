package com.example.ckh.foodtruck;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ckh on 2016-09-18.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title)
    {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitles.get(position);
    }
}
