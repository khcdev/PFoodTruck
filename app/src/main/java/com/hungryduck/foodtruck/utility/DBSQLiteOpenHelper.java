package com.hungryduck.foodtruck.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ckh on 2016-10-16.
 * SQLiteOpenHelper
 * onCreate  :  super로 올린 dbname과 같은 DB가 존재하지 않으면 호출된다. DB를 만들고 open한다.
 * onUpgrade : DB에 변동사항이 있는 경우에 발생하는 메서드 Version으로 구분
 */
public class DBSQLiteOpenHelper extends SQLiteOpenHelper {

    String tag="foodtruckDB";

    public DBSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        String table_SPOT_INFO = "create table SPOT_INFO(" +
                "SPOT_ID integer primary key, "+
                "MALE integer, "+
                "FEMALE integer, "+
                "TWYO_BELOW integer, "+
                "TWNT_THRTS integer, "+
                "FRTS_FFTS integer, "+
                "SXTS_ABOVE integer, "+
                "SPOT_NAME text, "+
                "X_POS real,"+
                "Y_POS real,"+
                "GU_ID integer,"+
                "GU_NAME text);";
        db.execSQL(table_SPOT_INFO);
        /*======================== refactor 이전 ========================*/
        String table_foodtruck = "create table foodtruck(" +
                "truck_id integer primary key," +
                "truck_name text," +
                "owner text," +
                "imgcode text," +
                "score real," +
                "favorites integer);";
        String table_review = "create table review(" +
                "_id integer primary key autoincrement," +
                "truck_id integer," +
                "writer text," +
                "contents text," +
                "score integer,"+
                "date text);";
        String table_menu = "create table menu(" +
                "_id integer primary key autoincrement," +
                "truck_id integer," +
                "menu_name text," +
                "recommend integer," +
                "origin text," +
                "price integer," +
                "imgcode text);";
        db.execSQL(table_foodtruck);
        Log.d(tag,"foodtruck table insertion");
        db.execSQL(table_review);
        Log.d(tag,"review table insertion");
        db.execSQL(table_menu);
        Log.d(tag,"menu table insertion");
        /*data insertion*/
        sql = "insert into foodtruck values(" +
                "101," +
                "'SteakOut'," +
                "'김성태'," +
                "'img_steakout_main'," +
                "4.2," +
                "23);";
        db.execSQL(sql);
        sql = "insert into foodtruck values(" +
                "102," +
                "'GoPizza'," +
                "'박진현'," +
                "'img_gopizza_main'," +
                "4.3," +
                "17);";
        db.execSQL(sql);
        sql = "insert into foodtruck values(" +
                "103," +
                "'청년반점'," +
                "'이하린'," +
                "'img_chungnyun_main'," +
                "4.3," +
                "17);";
        db.execSQL(sql);
        sql = "insert into menu values(null,101,'스테이크200g',1,'호주산',8000, 'img_steakout_m01');";
        db.execSQL(sql);

        sql = "insert into menu values(null,102,'그릴드불고기피자',1,'미국산',7000, 'img_gopizza_m01');";
        db.execSQL(sql);
        sql = "insert into menu values(null,102,'포테이토피자',0,'미국산',7000, 'img_gopizza_m02');";
        db.execSQL(sql);
        sql = "insert into menu values(null,102,'샐러드+콜라',0,'국산',2000, 'img_gopizza_m03');";
        db.execSQL(sql);

        sql = "insert into menu values(null,103,'레몬크림새우',1,'국산',8000, 'img_chungnyun_m01');";
        db.execSQL(sql);
        sql = "insert into menu values(null,103,'동파육',0,'국산',4500, 'img_chungnyun_m02');";
        db.execSQL(sql);

        sql = "insert into review values (null,101,'akex','너무 맛있네요!',4,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,101,'wtod','고기가 퍽퍽하지 않고 맛있습니다.',4,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,101,'ksad','다른 부위도 있었으면 좋겠습니다~',5,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,102,'pizzapizza','치즈 듬뿍 맛있었습니다.',5,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,102,'숯돌이','시카고 그릴드 피자 굿굿',3,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,102,'영광굴비','가격대비 최고!',4,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,103,'dasdas','레몬 크림새우 느끼하지 않고 너무 맛있네요~',4,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,103,'krez0521','이런 퀼리티의 동파육일꺼라곤 생각도 못했습니다~ 맛있네요',5,'2016-10-17');";
        db.execSQL(sql);
        sql = "insert into review values (null,103,'짜잔형','good~',3,'2016-10-17');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
