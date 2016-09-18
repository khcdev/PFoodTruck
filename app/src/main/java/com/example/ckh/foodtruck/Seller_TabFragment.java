package com.example.ckh.foodtruck;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

/**
 * Created by Ckh on 2016-09-18.
 */
public class Seller_TabFragment extends AppCompatActivity {
    FragmentInflater fi1;
    FragmentInflater fi2;
    FragmentInflater fi3;
    FragmentInflater fi4;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_fragment);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        fi1=new FragmentInflater();
        fi1.setresId(R.layout.seller_tab1_sales);
        adapter.addFragment(fi1, "영업");
        fi2=new FragmentInflater();
        fi2.setresId(R.layout.seller_tab2_stroemng);
       adapter.addFragment(fi2, "매장");
        fi3= new FragmentInflater();
        fi3.setresId(R.layout.seller_tab3_news);
        adapter.addFragment(fi3, "뉴스");
        fi4=new FragmentInflater();
        fi4.setresId(R.layout.seller_tab4_settings);
        adapter.addFragment(fi4, "설정");

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
