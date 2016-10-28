package com.example.ckh.foodtruck.seller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.ckh.foodtruck.R;
import kr.hyosang.coordinate.CoordPoint;
import kr.hyosang.coordinate.TransCoord;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Ckh on 2016-09-10.
 */
public class Seller_Login extends Activity{
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

                TransXCode = Math.round(TransXCode*10000)/10000.0;
                TransYCode = Math.round(TransYCode*10000)/10000.0;

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
            Toast.makeText(Seller_Login.this,"추출완료!",Toast.LENGTH_SHORT).show();

        }                    // for문 정렬
        catch (IOException e) {
        }
    }

    private void Gusort() {


        for (int i = 0; i < allofseoul.size(); i++) {
            m.quickSort(allofseoul.get(i), 0, allofseoul.get(i).size() - 1);
            Collections.reverse(allofseoul.get(i));
        }

    }
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.seller_main);

        ImageView sellerlogin = (ImageView) findViewById(R.id.sellermain_imgbtn_Login);
        final EditText i_login = (EditText) findViewById(R.id.seller_lgn_edit_text_id);
        final EditText i_password =(EditText) findViewById(R.id.seller_lgn_edit_text_password);

        sellerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean id_check=false;
                boolean pass_check=false;
                if(i_login.getText().toString().equals("gopizza")) id_check=true;
                if(i_password.getText().toString().equals("0000")) pass_check=true;
                Log.i("logindata",i_login.getText().toString()+id_check+","+i_password.getText().toString()+pass_check);
                if(id_check&&pass_check) {
                    Intent intent = new Intent(Seller_Login.this, Seller_TabFragment.class);
                    startActivity(intent);
                    finish();
                }
                else Toast.makeText(Seller_Login.this,"아이디 혹은 패스워드가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }
        });

        File();
        Gusort();
    }

}
