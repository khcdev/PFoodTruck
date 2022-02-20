package com.hungryduck.foodtruck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hungryduck.foodtruck.utility.MyRetrofit;
import com.hungryduck.foodtruck.utility.MySharedPreferences;
import com.hungryduck.viewmodel.restdataform.ServerVersionDTO;
import com.hungryduck.viewmodel.restdataform.SpotInformDTO;

import com.hungryduck.foodtruck.utility.GlobalApplication;
import com.hungryduck.foodtruck.utility.DBSQLiteOpenHelper;
import kr.hyosang.coordinate.CoordPoint;
import kr.hyosang.coordinate.TransCoord;

import retrofit2.Call;
import retrofit2.http.GET;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ckh on 2016-09-10.
 * Refactored by CKH on 2017-05-28.
 * 어플리케이션의 로딩 화면 Splash
 * 어플리케이션 실행에 필요한 기본 데이터들을 서버에서 가져와 저장한다.
 */

public class Splash extends Activity {
    private DBSQLiteOpenHelper helper;
    /*
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    */
    private SQLiteDatabase db;
    //ProgressBar progressBar;

    @Override
    public void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.splash_loading);
        //progressBar = (ProgressBar) findViewById(R.id.pgbar);
        Toast.makeText(Splash.this, "데이터를 읽어오는 중입니다.", Toast.LENGTH_LONG).show();
        MyAsyncTask asyncTask = new MyAsyncTask(Splash.this);
        asyncTask.execute(0);

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

    public void DBInsert(List<SpotInformDTO> data){

        db=helper.getWritableDatabase();
        // 데이터를 직접 sql 문에 포함 시키지 않고 '?'를 일단 입력하고, execute시에 객체를 넘겨주어 삽입 가능
        String sql ="insert into SPOT_INFO ( "+
                "SPOT_ID, MALE, FEMALE, TWYO_BELOW, TWNT_THRTS, FRTS_FFTS, SXTS_ABOVE, SPOT_NAME, "+
                "X_POS, Y_POS, GU_ID, GU_NAME ) "+
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        for(int i=0;i<data.size();i++){
            //좌표 변환
            CoordPoint pt = new CoordPoint(data.get(i).getX_POS(), data.get(i).getY_POS());
            CoordPoint ktmPt = TransCoord.getTransCoord(pt, TransCoord.COORD_TYPE_WTM, TransCoord.COORD_TYPE_WGS84);
            Double TransXCode = ktmPt.x;
            Double TransYCode = ktmPt.y;

            TransXCode = Math.round(TransXCode * 10000) / 10000.0;
            TransYCode = Math.round(TransYCode * 10000) / 10000.0;

            //sql execute
            db.execSQL(sql,
                    new Object[]{
                            data.get(i).getSPOT_ID(),
                            data.get(i).getMALE(),
                            data.get(i).getFEMALE(),
                            data.get(i).getTWYO_BELOW(),
                            data.get(i).getTWNT_THRTS(),
                            data.get(i).getFRTS_FFTS(),
                            data.get(i).getSXTS_ABOVE(),
                            data.get(i).getSPOT_NAME(),
                            TransXCode,
                            TransYCode,
                            data.get(i).getGU_ID(),
                            data.get(i).getGU_NAME()
                    });
        }
    }

    /*AsyncTask*/
    //UI스레드와 백그라운드/네트워크 작업을 분리하기 위함
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
            helper = new DBSQLiteOpenHelper(
                    Splash.this, GlobalApplication.dbName,
                    null,
                    MySharedPreferences.getInstance().getAppDBVersion(Splash.this)
            );
            //TODO : SharedPreferences 작동 확인하면 주석 지우기
            Log.d("작업 이전",Integer.toString(MySharedPreferences.getInstance().getAppDBVersion(Splash.this)));
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
            //서버에 GET 요청하여 app의 DB 버전과 서버의 DB 버전차이를 확인 하고 업데이트 내역을 가져오는 부분

            DBCheckService service_dbCheck = MyRetrofit.getInstance().getRetrofit().create(DBCheckService.class);
            Call<List<ServerVersionDTO>> call_version = service_dbCheck.checkVersion();
            List<ServerVersionDTO> result_version=null;
            try {
                result_version = call_version.execute().body();
            }catch (IOException e){
                Log.e("Httperrckh","err");
                e.printStackTrace();
            }
            if(result_version!=null &&
                    result_version.get(0).getVersion() > MySharedPreferences.getInstance().getAppDBVersion(Splash.this)){
                GetDB service_getDB = MyRetrofit.getInstance().getRetrofit().create(GetDB.class);
                Call<List<SpotInformDTO>> call_data=service_getDB.getDBData();
                try{
                    //서버로부터 새로운 업데이트 내역 요청
                    List<SpotInformDTO> result_SpotDB = call_data.execute().body();
                    //가져온 데이터 내부 DB에 저장
                    DBInsert(result_SpotDB);
                }catch(IOException e){
                    e.printStackTrace();
                }
                //edit = pref.edit();
                //SharedPreference에 업데이트 버전 저장
                MySharedPreferences.getInstance().setAppDBVersion(Splash.this,result_version.get(0).getVersion());
                //edit.putInt("AppVersion",result_version.get(0).getVersion());
                db.close();
                //edit.commit();
            }
            publishProgress("progress", Integer.toString(10),
                    "poidata init");
            publishProgress("progress", Integer.toString(30),
                    "poidata init");
            //FIXME : 이미지 파일을 디바이스 내장 스토리지에 저장하는 부분이기 때문에 굉장히 시간 소모가 크다.
            //FIXME : 이미지 서버에 post 요청하는 부분까지는 불가능 할듯, 기본 트럭,메뉴 이미지 불러오는것만 서버에서 처리

            if (!MySharedPreferences.getInstance().getImgFirst(Splash.this)) {
                /*edit = pref.edit();
                edit.putBoolean("isFirst", true);
                edit.commit();*/
                MySharedPreferences.getInstance().setImgFirst(Splash.this,true);
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
        //백그라운드 작업 도중에 UI작업을 할 수 있는 스레드
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

    public interface DBCheckService{
        @GET("newdata/checkversion")
        Call<List<ServerVersionDTO>> checkVersion();
    }
    public interface GetDB{
        @GET("newdata")
        Call<List<SpotInformDTO>> getDBData();
    }

}
