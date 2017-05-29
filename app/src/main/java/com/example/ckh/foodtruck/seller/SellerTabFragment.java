package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.ckh.foodtruck.externLibrary.GlobalApplication;
import com.example.ckh.foodtruck.R;
import com.example.ckh.restdataform.DBSQLiteOpenHelper;
import com.example.ckh.ViewDTO.SpotInformDTO;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ckh on 2016-09-18.
 */
@SuppressLint("DefaultLocale")
public class SellerTabFragment extends FragmentActivity {
    SellerSectionPagerAdapter mSectionPagerAdapter;
    ViewPager mViewPager;
    DBSQLiteOpenHelper helper;
    SQLiteDatabase db;
    SharedPreferences pref;
    ArrayList<String> guNameList = new ArrayList<>();
    ArrayList<SpotInformDTO> spotInformDTOList = new ArrayList<>();
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_fragment);
        //DB version을 얻기 위한 pref 호출
        pref = getSharedPreferences("Version", MODE_PRIVATE);
        //DB hepler - 데이터 쿼리
        Log.e("appversion잘?", Integer.toString(pref.getInt("appVersion", 1)));
        helper = new DBSQLiteOpenHelper(
                getApplicationContext(),
                GlobalApplication.dbName,
                null,
                pref.getInt("appVersion", 1)
        );
        //읽기 권한
        db = helper.getReadableDatabase();
        //먼저 구 이름을 쿼리한후 iterator를 이용하여 구별로 상위 5개를 쿼리한다.
        sql = "select GU_NAME from SPOT_INFO group by GU_NAME;";
        Cursor c1 = db.rawQuery(sql, null);
        int vr = 0;
        while (c1.moveToNext()) {
            guNameList.add(c1.getString(0));
            vr++;
        }
        Log.e("과연 호출?", Integer.toString(vr));
        c1.close();
        //쿼리가 정상적으로 진행 되었을 경우
        SpotInformDTO spotInformDTO = new SpotInformDTO();
        if (guNameList.size() != 0) {
            Iterator<String> itr = guNameList.iterator();
            while (itr.hasNext()) {
                sql = "select " +
                        "SPOT_ID, MALE, FEMALE, TWYO_BELOW, TWNT_THRTS, FRTS_FFTS, SXTS_ABOVE, " +
                        "SPOT_NAME, X_POS, Y_POS, GU_ID, GU_NAME" +
                        " from SPOT_INFO where GU_NAME=\"" + itr.next() +
                        "\" order by MALE+FEMALE desc limit 5;";
                Cursor c2 = db.rawQuery(sql, null);
                while (c2.moveToNext()) {
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
        mSectionPagerAdapter = new SellerSectionPagerAdapter(getApplicationContext(), getSupportFragmentManager());

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
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        //getMenuInflater().inflate(R.);
        return true;
    }*/


}

