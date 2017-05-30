package com.example.ckh.foodtruck.seller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import android.widget.Toast;
import com.example.ckh.foodtruck.R;
import com.example.ckh.viewdto.restdataform.SpotInformDTO;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SpotDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spotdetail);

        int totalAge,maleRatio,femaleRatio,totalGender;
        float twyoBelowRatio,twnt_thrtsRatio,frts_fftsRatio,sxts_aboveRatio;

        Intent intent = getIntent();
        boolean check = intent.getBooleanExtra("check",false);
        //TODO : 객체 checking 후에 띄우기
        if(check) {
            SpotInformDTO spotInformDTO = (SpotInformDTO) intent.getSerializableExtra("SpotInfoObj");
            TextView man_tv = (TextView) findViewById(R.id.mantv);
            TextView woman_tv = (TextView) findViewById(R.id.womantv);
            TextView gu_tv = (TextView) findViewById(R.id.guinfo);
            PieChart pieChart = (PieChart) findViewById(R.id.Piechart);

            gu_tv.setText(spotInformDTO.getGU_NAME() + " " + spotInformDTO.getSPOT_NAME() + "의 유동인구 분석입니다.");
            totalAge = spotInformDTO.getTWYO_BELOW() + spotInformDTO.getTWNT_THRTS() + spotInformDTO.getFRTS_FFTS() + spotInformDTO.getSXTS_ABOVE();
            totalGender = spotInformDTO.getFEMALE() + spotInformDTO.getMALE();

            twyoBelowRatio = (float) (spotInformDTO.getTWYO_BELOW() * 100) / totalAge;
            twnt_thrtsRatio = (float) (spotInformDTO.getTWNT_THRTS() * 100) / totalAge;
            frts_fftsRatio = (float) (spotInformDTO.getFRTS_FFTS() * 100) / totalAge;
            sxts_aboveRatio = (float) (spotInformDTO.getSXTS_ABOVE() * 100) / totalAge;

            maleRatio = (int) ((float) (spotInformDTO.getFEMALE() * 100) / totalGender);
            femaleRatio = 100 - maleRatio;

            man_tv.setText("" + maleRatio + "%");
            woman_tv.setText("" + femaleRatio + "%");
            ArrayList<Entry> entries = new ArrayList<Entry>();

            entries.add(new Entry(twyoBelowRatio, 0));
            entries.add(new Entry(twnt_thrtsRatio, 1));
            entries.add(new Entry(frts_fftsRatio, 2));
            entries.add(new Entry(sxts_aboveRatio, 3));      // MPAndroidGrahpChart 에서 사용되는 데이터 입력방법대로 데이터 삽입
            PieDataSet dataSet = new PieDataSet(entries, null);      // 데이터 적용시키면서 설명달기
            ArrayList<String> labels = new ArrayList<String>();
            labels.add("20대 미만");
            labels.add("20대-30대");
            labels.add("40대-50대");
            labels.add("60대 이상");   // 라벨 달기
            pieChart.setUsePercentValues(true);
            pieChart.setDescription("단위 : (%)");      // 단위에 대한 설명 달기
            dataSet.setColors(ColorTemplate.COLORFUL_COLORS);       // 기본 컬러 탬플릿 사용
            pieChart.animateY(5000);        // 애니메이션 효과 적용
            PieData data = new PieData(labels, dataSet);     // 라벨과 데이터를 연결
            pieChart.setData(data);     // 이를 pieChart 뷰에 적용
            data.setValueTextSize(15);      // 차트의 글씨크기 결정(dp단위, 수는 float)
            pieChart.setDescriptionTextSize(15);    // description도 글씨 크기 설정
            // System.out.println(totalAge+","+totalGender+","+twyoBelowRatio+","+twnt_thrtsRatio+","+frts_fftsRatio+","+sxts_aboveRatio);
            Legend legend = pieChart.getLegend();

            legend.setTextSize(15);
        }else{
            //spot 정보를 나타 낼 수 없습니다.
            Toast.makeText(this,"유동인구 데이터를 나타낼 수 없습니다.",Toast.LENGTH_LONG).show();

        }
    }

}