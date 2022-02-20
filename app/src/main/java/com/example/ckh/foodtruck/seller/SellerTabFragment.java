package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.utility.MySharedPreferences;
import com.example.ckh.viewdto.restdataform.SpotInformDTO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ckh on 2016-09-18.
 */
@SuppressLint("DefaultLocale")
public class SellerTabFragment extends FragmentActivity {

    private ArrayList<SpotInformDTO> spotInformDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_fragment);

        ViewPager mViewPager;
        DBSQLiteOpenHelper helper;
        SQLiteDatabase db;

        helper = new DBSQLiteOpenHelper(
                getApplicationContext(),
                GlobalApplication.dbName,
                null,
                MySharedPreferences.getInstance().getAppDBVersion(SellerTabFragment.this)
        );
        //읽기 권한
        db = helper.getReadableDatabase();
        //먼저 구 이름을 쿼리한후 iterator를 이용하여 구별로 상위 5개를 쿼리한다.
        String sql1 = "select GU_NAME from SPOT_INFO group by GU_NAME;";
        Cursor c1 = db.rawQuery(sql1, null);
        ArrayList<String> guNameList = new ArrayList<>();
        while (c1.moveToNext()) guNameList.add(c1.getString(0));
        Log.e("구데이터",Integer.toString(guNameList.size()));
        c1.close();
        //쿼리가 정상적으로 진행 되었을 경우
        //
        if (guNameList.size() != 0) {
            Iterator<String> itr = guNameList.iterator();
            while (itr.hasNext()) {
                String sql2 = "select " +
                        "SPOT_ID, MALE, FEMALE, TWYO_BELOW, TWNT_THRTS, FRTS_FFTS, SXTS_ABOVE, " +
                        "SPOT_NAME, X_POS, Y_POS, GU_ID, GU_NAME" +
                        " from SPOT_INFO where GU_NAME=\"" + itr.next() +
                        "\" order by MALE+FEMALE desc limit 5;";
                Cursor c2 = db.rawQuery(sql2, null);
                while (c2.moveToNext()) {
                    SpotInformDTO spotInformDTO = new SpotInformDTO();
                    spotInformDTO.setSPOT_ID(c2.getInt(0));
                    spotInformDTO.setMALE(c2.getInt(1));
                    spotInformDTO.setFEMALE(c2.getInt(2));
                    spotInformDTO.setTWYO_BELOW(c2.getInt(3));
                    spotInformDTO.setTWNT_THRTS(c2.getInt(4));
                    spotInformDTO.setFRTS_FFTS(c2.getInt(5));
                    spotInformDTO.setSXTS_ABOVE(c2.getInt(6));
                    spotInformDTO.setSPOT_NAME(c2.getString(7));
                    spotInformDTO.setX_POS(c2.getDouble(8));
                    spotInformDTO.setY_POS(c2.getDouble(9));
                    spotInformDTO.setGU_ID(c2.getInt(10));
                    spotInformDTO.setGU_NAME(c2.getString(11));
                    spotInformDTOList.add(spotInformDTO);
                }
                c2.close();
            }
            Log.e("spotnumberquery", Integer.toString(spotInformDTOList.size()));
        } else {      //Gu정보를 제대로 쿼리 해오지 못한 경우

        }
        Log.e("spotNumberAct", Integer.toString(spotInformDTOList.size()));
        SellerSectionPagerAdapter mSectionPagerAdapter = new SellerSectionPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.seller_viewpager);
        mViewPager.setAdapter(mSectionPagerAdapter);

        TabLayout SellerTab = (TabLayout) findViewById(R.id.seller_tabs);
        SellerTab.setupWithViewPager(mViewPager);

    }

    @SuppressLint("DefaultLocale")
    public class SellerSectionPagerAdapter extends FragmentPagerAdapter {
        Context mContext;

        public SellerSectionPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        /*fragment page open*/
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SellerTabFirstSales(mContext, spotInformDTOList);
                case 1:
                    return new SellerTabSecStore(mContext);
                case 2:
                    return new SellerTabTriNews(mContext);
                case 3:
                    return new SellerTabFourSettings(mContext);
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
        public CharSequence getPageTitle(int position) {
            switch (position) {
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

}

