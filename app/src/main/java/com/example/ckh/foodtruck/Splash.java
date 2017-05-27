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
import com.google.gson.annotations.SerializedName;
import kr.hyosang.coordinate.CoordPoint;
import kr.hyosang.coordinate.TransCoord;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    SharedPreferences appVersion,pref;
    SharedPreferences.Editor edit;
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
                        (int) (Double.parseDouble(row.getCell(j + 6).toString())),
                        row.getCell(j + 7).toString(),
                        Double.parseDouble(row.getCell(j + 8).toString()),
                        Double.parseDouble(row.getCell(j + 9).toString()),
                        row.getCell(10).toString(),
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
    /*AsyncTask*/
    //execute() 메서드를 호출 함으로써 AsyncTask를 실행합니다.
    class MyAsyncTask extends AsyncTask<Integer, String, Integer>{
        private ProgressDialog mDlg;

        private Context mContext;
        public MyAsyncTask(Context context){
            mContext=context;
        }
        //AsyncTask로 백그라운드 작업을 실행하기 전에 실행되는 onPreExecuted 메서드
        //ex 이미지를 불러올때 로딩중.. 이란 팝업을 띄우는등 스레드 작업 이전에
        //수행할 동작을 구현함.
        @Override
        protected void onPreExecute() {
            /*mDlg = new ProgressDialog(Splash.this);
            //FIXME : 프로그레스 스타일 spinner로 변경
            mDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDlg.setMessage("작업 시작");
            mDlg.show();
            */
            super.onPreExecute();
        }
        //실제로 백그라운드 작업을 수행하는 메서드
        //execute 메서드를 호출 할 때 사용된 파라미터를 전달 받습니다.
        //doInBackGround() 에서 중간 중간 진행 상태를 UI에 업데이트 하도록 하려면
        // publishProgress() 메소드를 호출하여 진행한다.
        @Override
        protected Integer doInBackground(Integer... params) {
            publishProgress("max", Integer.toString(100));
            /** REFACTORING*/
            //module
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.78.234.100:3040/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            DBCheckService service = retrofit.create(DBCheckService.class);
            Call<List<ServerVersion>> call = service.checkVersion();
            List<ServerVersion> result=null;
            try {
                result = call.execute().body();
                Log.e("Http",Integer.toString(result.get(0).version));
            }catch (IOException e){
                Log.e("Httperrckh","err");
                e.printStackTrace();
            }
            appVersion = getSharedPreferences("appVersion", Activity.MODE_PRIVATE);
            if(result!=null && result.get(0).version>appVersion.getInt("appVersion",1)){
                //데이터 input

                //좌표 변환
                //CoordPoint pt = new CoordPoint(temp1.Xcode, temp1.Ycode);
                //CoordPoint ktmPt = TransCoord.getTransCoord(pt, TransCoord.COORD_TYPE_WTM, TransCoord.COORD_TYPE_WGS84);
                //Double TransXCode = ktmPt.x;
                //Double TransYCode = ktmPt.y;

                //TransXCode = Math.round(TransXCode * 10000) / 10000.0;
                //TransYCode = Math.round(TransYCode * 10000) / 10000.0;

                helper = new DBSQLiteOpenHelper(Splash.this,GlobalApplication.dbName,null,appVersion.getInt("appVersion",1));
                //TODO : DB Helper 초기화 하면서 테이블 정의하고 데이터 삽입하는 부분 모듈화 하여 따로 처리한다.
                db=helper.getWritableDatabase();
                String sql ="insert into SPOT_INFO values("+
                            ""

                        ;
                //db.execSQL();
                //TODO : http 요청 모듈 필요
                edit = appVersion.edit();
                edit.putInt("appVersion",result.get(0).version);
                db.close();
                edit.commit();
            }
            publishProgress("progress", Integer.toString(10),
                    "poidata init");
            //FIXME : DB처리하는 부분이 해결 되면 삭제 해도 되는 부분
            File();
            Gusort();
            publishProgress("progress", Integer.toString(30),
                    "poidata init");

            pref = getSharedPreferences("appVersion", Activity.MODE_PRIVATE);
            if (!pref.getBoolean("isFirst", false)) {
                edit = pref.edit();
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
            /*if (params[0].equals("progress")) {
                mDlg.setProgress(Integer.parseInt(params[1]));
                mDlg.setMessage(params[2]);
            } else if (params[0].equals("max")) {
                mDlg.setMax(Integer.parseInt(params[1]));
            }*/
            if (params[0].equals("progress")) Log.d("progress","t");
            if (params[0].equals("max")) Log.d("progress","next");
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            //mDlg.dismiss();

            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            Splash.this.startActivity(mainIntent);
            Splash.this.finish();
            Log.d("Result", "result : " + result);
        }
    }
    public class ServerVersion{
        @SerializedName("version")
        int version;
    }
    /*public class SpotInform{
        @SerializedName("SPOT_ID")
        String SPOT_ID;
        String MALE;
        String FEMALE;

    }*/
    public interface DBCheckService{
        @GET("newdata/checkversion")
        Call<List<ServerVersion>> checkVersion();
    }
    /*public interface getDB{
        @GET("newdata")
        Call<List<>>
    }*/

}
