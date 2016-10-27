package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.seller.MovingPeople;
import com.example.ckh.foodtruck.seller.Seller_Login;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Main2Activity extends Activity {


    private MovingPeople targetRatio;
    private int maleRatio;
    private int femaleRatio;
    private int totalGender;
    private float twyoBelowRatio;
    private float twnt_thrtsRatio;
    private float frts_fftsRatio;
    private float sxts_aboveRatio;
    private int totalAge;

    TextView mantv;
    TextView womantv;
    TextView gutv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mantv = (TextView)findViewById(R.id.mantv);
        womantv = (TextView)findViewById(R.id.womantv);
        gutv = (TextView)findViewById(R.id.guinfo);

        Intent intent = getIntent();
        String title = intent.getStringExtra("ID");

        PieChart pieChart = (PieChart) findViewById(R.id.Piechart);

        // targetRatio = (MovingPeople) getIntent().getSerializableExtra(getKeys());     // 직렬화 된 객체를 받아옴 어떤 객체인지 표시해주기 위해 구분되는 것은 key값이므로, 이를 받는 메서드도 사용

        for(int i = 0; i< Seller_Login.allSeoul.size(); i++)
        {
            if(Seller_Login.allSeoul.get(i).examin_spot_name.equals(title))
            {
                targetRatio=Seller_Login.allSeoul.get(i);
                break;
            }
        }

        gutv.setText(targetRatio.guname+" "+targetRatio.examin_spot_name+"의 유동인구 분석입니다.");

        makingRatio();      // 비율을 만들어 줌
        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(twyoBelowRatio, 0));
        entries.add(new Entry(twnt_thrtsRatio, 1));
        entries.add(new Entry(frts_fftsRatio, 2));
        entries.add(new Entry(sxts_aboveRatio, 3));      // MPAndroidGrahpChart 에서 사용되는 데이터 입력방법대로 데이터 삽입
        PieDataSet dataSet = new PieDataSet(entries,null );      // 데이터 적용시키면서 설명달기
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
        Legend legend=pieChart.getLegend();

        legend.setTextSize(15);

    }

    private void makingRatio() {
        totalAge = targetRatio.twyoBelow + targetRatio.twnt_thrts + targetRatio.frts_ffts + targetRatio.sxts_above;
        totalGender = targetRatio.female + targetRatio.male;

        twyoBelowRatio = (float) (targetRatio.twyoBelow * 100) / totalAge;
        twnt_thrtsRatio = (float) (targetRatio.twnt_thrts * 100) / totalAge;
        frts_fftsRatio = (float) (targetRatio.frts_ffts * 100) / totalAge;
        sxts_aboveRatio = (float) (targetRatio.sxts_above * 100) / totalAge;

        maleRatio = (int) ((float) (targetRatio.male * 100) / totalGender);
        femaleRatio = 100-maleRatio;

        mantv.setText(""+maleRatio+"%");
        womantv.setText(""+femaleRatio+"%");
    }

    private String getKeys() {
        Intent pastIntent = getIntent();      // 호출한 인텐트를 받아와서
        Bundle bundle = pastIntent.getExtras();      // 번들형태로 Extras뽑아내기 그럼 예를들어 first={ v1,v2,...,vn}이렇게 나오는데 우리는 저 first를 뽑아낼 것
        Set<String> tmp = bundle.keySet();        // Set형태의 리스트에 키값 저장
        Iterator<String> bb = tmp.iterator();


        return bb.next();
    }
}