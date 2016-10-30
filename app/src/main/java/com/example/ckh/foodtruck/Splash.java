package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.seller.MakingAbove5;
import com.example.ckh.foodtruck.seller.MovingPeople;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import kr.hyosang.coordinate.CoordPoint;
import kr.hyosang.coordinate.TransCoord;

/**
 * Created by Ckh on 2016-09-10.
 * 어플리케이션의 로딩 화면 Splash
 * <p>
 * 초기 데이터 세팅 진행
 * - db오픈 및 초기 데이터 세팅 값 저장
 * - drawable resource 앱 설치후 내부 저장소에 따로 저장
 */

public class Splash extends Activity {
    DBSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    String tag = "SQLite";
    ProgressBar progressBar;

    //로딩 화면이 떠있는 시간(밀리초단위)
    private final int SPLASH_DISPLAY_LENGTH = 4500;

    public static ArrayList<MovingPeople> dobong = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gangbuk = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> nowon = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> eunpyoung = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> seoungbook = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> jonglo = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> dongdaemoon = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> jungrang = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> seodaemoon = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> junggu = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> sungdong = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gwangjin = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> youngsan = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> mapo = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gangseo = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> yangcheon = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> guro = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> youngdengpo = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gumcheon = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gwhanak = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> dongjak = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> seocho = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gangnam = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> songpa = new ArrayList<MovingPeople>();
    public static ArrayList<MovingPeople> gangdong = new ArrayList<MovingPeople>();

    public static ArrayList<MovingPeople> allSeoul = new ArrayList<MovingPeople>();

    public static ArrayList<ArrayList<MovingPeople>> allofseoul = new ArrayList<>();

    HSSFRow row;
    MakingAbove5 m = new MakingAbove5();

    private void File() {
        try {
            InputStream in = getResources().getAssets().open("movingPeople.xls");
            POIFSFileSystem fileSystem = new POIFSFileSystem(in);
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1, j = 0; i < rows; i = i + 2) {

                row = sheet.getRow(i);    //i번째 행을 읽는다 첫행제외

                MovingPeople temp1 = new MovingPeople(row.getCell(j).toString(),
                        (int) (Double.parseDouble(row.getCell(j + 1).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 2).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 3).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 4).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 5).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 6).toString())), row.getCell(j + 7).toString(),
                        Double.parseDouble(row.getCell(j + 8).toString()),
                        Double.parseDouble(row.getCell(j + 9).toString()), row.getCell(j + 10).toString(),
                        row.getCell(j + 11).toString());

                row = sheet.getRow(i + 1);        //두개를 합쳐야 하기 때문에 i+1행을 읽어온다.

                MovingPeople temp2 = new MovingPeople(row.getCell(j).toString(),
                        (int) (Double.parseDouble(row.getCell(j + 1).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 2).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 3).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 4).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 5).toString())),
                        (int) (Double.parseDouble(row.getCell(j + 6).toString())), row.getCell(j + 7).toString(),
                        Double.parseDouble(row.getCell(j + 8).toString()),
                        Double.parseDouble(row.getCell(j + 9).toString()), row.getCell(10).toString(),
                        row.getCell(j + 11).toString());

                CoordPoint pt = new CoordPoint(temp1.Xcode, temp1.Ycode);
                CoordPoint ktmPt = TransCoord.getTransCoord(pt, TransCoord.COORD_TYPE_WTM, TransCoord.COORD_TYPE_WGS84);
                Double TransXCode = ktmPt.x;
                Double TransYCode = ktmPt.y;

                TransXCode = Math.round(TransXCode * 10000) / 10000.0;
                TransYCode = Math.round(TransYCode * 10000) / 10000.0;

                MovingPeople temp3 = new MovingPeople(temp1.examin_spot_cd, temp1.male + temp2.male,
                        temp1.female + temp2.female, temp1.twyoBelow + temp2.twyoBelow,
                        temp1.twnt_thrts + temp2.twnt_thrts, temp1.frts_ffts + temp2.frts_ffts,
                        temp1.sxts_above + temp2.sxts_above, temp1.examin_spot_name, TransXCode, TransYCode,
                        temp1.guCode, temp1.guname);

                allSeoul.add(temp3);
                //guname별로 case문을 통해 데이터를 입력함
                switch (temp3.guname) {
                    case "종로구":
                        jonglo.add(temp3);
                        break;

                    case "중구":
                        junggu.add(temp3);
                        break;

                    case "용산구":
                        youngsan.add(temp3);
                        break;

                    case "성동구":
                        sungdong.add(temp3);
                        break;

                    case "광진구":
                        gwangjin.add(temp3);
                        break;

                    case "동대문구":
                        dongdaemoon.add(temp3);
                        break;

                    case "중랑구":
                        jungrang.add(temp3);
                        break;

                    case "성북구":
                        seoungbook.add(temp3);
                        break;

                    case "강북구":
                        gangbuk.add(temp3);
                        break;

                    case "도봉구":
                        dobong.add(temp3);
                        break;

                    case "노원구":
                        nowon.add(temp3);
                        break;

                    case "은평구":
                        eunpyoung.add(temp3);
                        break;

                    case "서대문구":
                        seodaemoon.add(temp3);
                        break;

                    case "마포구":
                        mapo.add(temp3);
                        break;

                    case "양천구":
                        yangcheon.add(temp3);
                        break;

                    case "강서구":
                        gangseo.add(temp3);
                        break;

                    case "구로구":
                        guro.add(temp3);
                        break;

                    case "금천구":
                        gumcheon.add(temp3);
                        break;

                    case "영등포구":
                        youngdengpo.add(temp3);
                        break;

                    case "동작구":
                        dongjak.add(temp3);
                        break;

                    case "관악구":
                        gwhanak.add(temp3);
                        break;

                    case "서초구":
                        seocho.add(temp3);
                        break;

                    case "강남구":
                        gangnam.add(temp3);
                        break;

                    case "송파구":
                        songpa.add(temp3);
                        break;

                    case "강동구":
                        gangdong.add(temp3);
                        break;
                }


            }
            allofseoul.add(dobong);
            allofseoul.add(seoungbook);
            allofseoul.add(gangbuk);
            allofseoul.add(nowon);
            allofseoul.add(eunpyoung);
            allofseoul.add(dongdaemoon);
            allofseoul.add(jungrang);
            allofseoul.add(seodaemoon);
            allofseoul.add(junggu);
            allofseoul.add(sungdong);
            allofseoul.add(gwangjin);
            allofseoul.add(youngsan);
            allofseoul.add(mapo);
            allofseoul.add(gangseo);
            allofseoul.add(dobong);
            allofseoul.add(yangcheon);
            allofseoul.add(guro);
            allofseoul.add(youngdengpo);
            allofseoul.add(gumcheon);
            allofseoul.add(gwhanak);
            allofseoul.add(dongjak);
            allofseoul.add(seocho);
            allofseoul.add(gangnam);
            allofseoul.add(songpa);
            allofseoul.add(gangdong);

        } catch (IOException e) {
        }
    }

    private void Gusort() {


        for (int i = 0; i < allofseoul.size(); i++) {
            m.quickSort(allofseoul.get(i), 0, allofseoul.get(i).size() - 1);
            Collections.reverse(allofseoul.get(i));
        }

    }

    @Override
    public void onCreate(Bundle b) {
        SharedPreferences pref;
        super.onCreate(b);
        setContentView(R.layout.splash_loading);
        progressBar = (ProgressBar) findViewById(R.id.pgbar);

        Toast.makeText(Splash.this, "데이터를 읽어오는 중입니다.", Toast.LENGTH_LONG).show();


        File();
        Gusort();

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (!pref.getBoolean("isFirst", false)) {
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();

            saveFiletoInternalStorage(R.drawable.img_chungnyun_m01, "img_chungnyun_m01.png");
            saveFiletoInternalStorage(R.drawable.img_chungnyun_m02, "img_chungnyun_m02.png");
            saveFiletoInternalStorage(R.drawable.img_chungnyun_main, "img_chungnyun_main.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m01, "img_gopizza_m01.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m02, "img_gopizza_m02.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_m03, "img_gopizza_m03.png");
            saveFiletoInternalStorage(R.drawable.img_gopizza_main, "img_gopizza_main.png");
            saveFiletoInternalStorage(R.drawable.img_steakout_m01, "img_steakout_m01.png");
            saveFiletoInternalStorage(R.drawable.img_steakout_main, "img_steakout_main.png");


        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                helper = new DBSQLiteOpenHelper(
                        Splash.this,
                        GlobalApplication.dbName,
                        null,
                        1
                );
                try {
                    db = helper.getWritableDatabase();
                } catch (SQLiteException e) {
                    e.printStackTrace();
                    Log.e(tag, "데이터베이스 생성/열기 실패");
                    Toast.makeText(Splash.this, "DB opening failed", Toast.LENGTH_LONG).show();
                }
                GlobalApplication.truckintro = new HashMap<String, String>();
                GlobalApplication.truckintro.put("101", "합리적인 가격에 맛있는 스테이크를 제공합니다.");
                GlobalApplication.truckintro.put("102", "화덕 피자 전문점");
                GlobalApplication.truckintro.put("103", "최고 인기메뉴 레몬크림새우, 늦으면 즐기실수 없는 동파육!");

                GlobalApplication.trucknoti = new HashMap<String, String>();
                GlobalApplication.trucknoti.put("101", "11월 8일 4시~8시 왕십리역 akplaza 뒤편(먹자골목)");
                GlobalApplication.trucknoti.put("102", "11월 3일 세종대학교 정문 11시~3시");
                GlobalApplication.trucknoti.put("103", "매주 금요일 토요일 밤도깨비 야시장에서 만나요 :)");
                    /* 메뉴액티비티를 실행하고 로딩화면(splash) 끝*/
                db.close();

                //Bitmap
                //Bitmap bm
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료한다.*/

    }

    public void saveFiletoInternalStorage(int rvalue, String str) {
        try {
            FileOutputStream fos = openFileOutput(str, 0);
            Bitmap bm = BitmapFactory.decodeResource(getResources(), rvalue);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Log.e("fileIO", str + "in");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("fileIOerror", "fileinerror");
        }

    }
}
