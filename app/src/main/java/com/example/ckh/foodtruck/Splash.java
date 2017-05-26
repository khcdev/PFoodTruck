package com.example.ckh.foodtruck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.ckh.foodtruck.database.DBSQLiteOpenHelper;
import com.example.ckh.foodtruck.seller.MakingAbove5;
import com.example.ckh.foodtruck.seller.MovingPeople;
import kr.hyosang.coordinate.CoordPoint;
import kr.hyosang.coordinate.TransCoord;
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
    SharedPreferences pref;
    private SQLiteDatabase db;
    String tag = "SQLite";
    ProgressBar progressBar;

    //로딩 화면이 떠있는 시간(밀리초단위)
    private final int SPLASH_DISPLAY_LENGTH = 4500;

    public static ArrayList<MovingPeople> dobong = new ArrayList<>();
    public static ArrayList<MovingPeople> gangbuk = new ArrayList<>();
    public static ArrayList<MovingPeople> nowon = new ArrayList<>();
    public static ArrayList<MovingPeople> eunpyoung = new ArrayList<>();
    public static ArrayList<MovingPeople> seoungbook = new ArrayList<>();
    public static ArrayList<MovingPeople> jonglo = new ArrayList<>();
    public static ArrayList<MovingPeople> dongdaemoon = new ArrayList<>();
    public static ArrayList<MovingPeople> jungrang = new ArrayList<>();
    public static ArrayList<MovingPeople> seodaemoon = new ArrayList<>();
    public static ArrayList<MovingPeople> junggu = new ArrayList<>();
    public static ArrayList<MovingPeople> sungdong = new ArrayList<>();
    public static ArrayList<MovingPeople> gwangjin = new ArrayList<>();
    public static ArrayList<MovingPeople> youngsan = new ArrayList<>();
    public static ArrayList<MovingPeople> mapo = new ArrayList<>();
    public static ArrayList<MovingPeople> gangseo = new ArrayList<>();
    public static ArrayList<MovingPeople> yangcheon = new ArrayList<>();
    public static ArrayList<MovingPeople> guro = new ArrayList<>();
    public static ArrayList<MovingPeople> youngdengpo = new ArrayList<>();
    public static ArrayList<MovingPeople> gumcheon = new ArrayList<>();
    public static ArrayList<MovingPeople> gwhanak = new ArrayList<>();
    public static ArrayList<MovingPeople> dongjak = new ArrayList<>();
    public static ArrayList<MovingPeople> seocho = new ArrayList<>();
    public static ArrayList<MovingPeople> gangdong = new ArrayList<>();
    public static ArrayList<MovingPeople> gangnam = new ArrayList<>();
    public static ArrayList<MovingPeople> songpa = new ArrayList<>();

    public static ArrayList<MovingPeople> allSeoul = new ArrayList<>();

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
                        (int) (Double.parseDouble(row.getCell(j + 6).toString())),
                        row.getCell(j + 7).toString(),
                        Double.parseDouble(row.getCell(j + 8).toString()),
                        Double.parseDouble(row.getCell(j + 9).toString()),
                        row.getCell(j + 10).toString(),
                        row.getCell(j + 11).toString()
                );

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
                //TODO : 좌표변환, pin
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

        super.onCreate(b);
        setContentView(R.layout.splash_loading);
        //progressBar = (ProgressBar) findViewById(R.id.pgbar);

        Toast.makeText(Splash.this, "데이터를 읽어오는 중입니다.", Toast.LENGTH_LONG).show();
        MyAsyncTask tast = new MyAsyncTask(Splash.this);
        tast.execute(0);

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
    class MyAsyncTask extends AsyncTask<Integer, String, Integer>{
        private ProgressDialog mDlg;

        private Context mContext;
        public MyAsyncTask(Context context){
            mContext=context;
        }
        @Override
        protected void onPreExecute() {
            mDlg = new ProgressDialog(Splash.this);
            mDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDlg.setMessage("작업 시작");
            mDlg.show();

            super.onPreExecute();

        }
        @Override
        protected Integer doInBackground(Integer... params) {
            publishProgress("max", Integer.toString(100));
            helper = new DBSQLiteOpenHelper(Splash.this,GlobalApplication.dbName,null,1);
            /** REFACTORING*/
            //TODO : DB Helper 초기화 하면서 테이블 정의하고 데이터 삽입하는 부분 모듈화 하여 따로 처리한다.
            db=helper.getWritableDatabase();
            //TODO : http 요청 모듈 필요
            //module

            //FIXME : DB처리하는 부분이 해결 되면 삭제 해도 되는 부분
            File();
            Gusort();
            publishProgress("progress", Integer.toString(30),
                    "poidata init");
            pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
            if (!pref.getBoolean("isFirst", false)) {
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("isFirst", true);
                edit.commit();
                //FIXME : 이미지 파일을 디바이스 내장 스토리지에 저장하는 부분이기 때문에 굉장히 시간 소모가 크다.
                //FIXME : 이미지 서버에 post 요청하는 부분까지는 불가능 할듯, 기본 트럭,메뉴 이미지 불러오는것만 서버에서 처리
                saveFiletoInternalStorage(R.drawable.img_chungnyun_m01, "img_chungnyun_m01.png");
                saveFiletoInternalStorage(R.drawable.img_chungnyun_m02, "img_chungnyun_m02.png");
                saveFiletoInternalStorage(R.drawable.img_chungnyun_main, "img_chungnyun_main.png");
                saveFiletoInternalStorage(R.drawable.img_gopizza_m01, "img_gopizza_m01.png");
                saveFiletoInternalStorage(R.drawable.img_gopizza_m02, "img_gopizza_m02.png");
                saveFiletoInternalStorage(R.drawable.img_gopizza_m03, "img_gopizza_m03.png");
                saveFiletoInternalStorage(R.drawable.img_gopizza_main, "img_gopizza_main.png");
                saveFiletoInternalStorage(R.drawable.img_steakout_m01, "img_steakout_m01.png");
                saveFiletoInternalStorage(R.drawable.img_steakout_main, "img_steakout_main.png");
                publishProgress("progress", Integer.toString(70),
                        "img data in");
            }
            publishProgress("progress", Integer.toString(80),
                    "hash data");
            GlobalApplication.truckintro = new HashMap<String, String>();
            GlobalApplication.truckintro.put("101", "합리적인 가격에 맛있는 스테이크를 제공합니다.");
            GlobalApplication.truckintro.put("102", "화덕 피자 전문점");
            GlobalApplication.truckintro.put("103", "최고 인기메뉴 레몬크림새우, 늦으면 즐기실수 없는 동파육!");

            GlobalApplication.trucknoti = new HashMap<String, String>();
            GlobalApplication.trucknoti.put("101", "11월 8일 4시~8시 왕십리역 akplaza 뒤편(먹자골목)");
            GlobalApplication.trucknoti.put("102", "11월 3일 세종대학교 정문 11시~3시");
            GlobalApplication.trucknoti.put("103", "매주 금요일 토요일 밤도깨비 야시장에서 만나요 :)");
                    /* 메뉴액티비티를 실행하고 로딩화면(splash) 끝*/
            publishProgress("progress", Integer.toString(100),
                    "complete");

            return 100;
        }
        @Override
        protected void onProgressUpdate(String... params) {
            if (params[0].equals("progress")) {
                mDlg.setProgress(Integer.parseInt(params[1]));
                mDlg.setMessage(params[2]);
            } else if (params[0].equals("max")) {
                mDlg.setMax(Integer.parseInt(params[1]));
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            mDlg.dismiss();
            db.close();
            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            Splash.this.startActivity(mainIntent);
            Splash.this.finish();
            Log.d("Result", "result : " + result);
        }
    }
}
