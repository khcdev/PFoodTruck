package com.example.ckh.foodtruck.seller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.androidquery.AQuery;
import com.example.ckh.cstview.Seller_News_EventFull;
import com.example.ckh.cstview.Seller_News_EventList;
import com.example.ckh.foodtruck.R;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-03.
 */
@SuppressLint("ValidFragment")
public class Seller_TabTri_News extends Fragment {
    Context mContext;
    View view;
    public Seller_TabTri_News(Context context){
        mContext= context;
    }
    private Seller_News_Parsing parser;
    private ListView seoulEventListView = null;
    private AQuery aq=new AQuery(getContext());
    private ListViewAdapter seoulEventAdapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        if(view == null){
            view = inflater.inflate(R.layout.seller_tab3_news_list,null);
        }
        seoulEventListView = (ListView) view.findViewById(R.id.seoulevent_menu_list);
        ListViewAdapter seoulEventAdapter = new ListViewAdapter(getActivity());
        seoulEventListView.setAdapter(seoulEventAdapter);

        StrictMode.enableDefaults();

        parser = new Seller_News_Parsing();     // 파싱하는 객체 생성
        new FestAsync().execute(null, null, null);  // 비동기식 스레드 시작하기

        return view;
    }



    private class ViewHolder {
        public ImageView eventImage;
        public TextView eventName;
        public TextView eventDate;
        public TextView eventPlace;
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context seoulEventContext = null;
        private ArrayList<Seller_News_EventList> seoulEventListData = new ArrayList<Seller_News_EventList>();

        public ListViewAdapter(Context seoulEventContext) {
            super();
            this.seoulEventContext = seoulEventContext;
        }

        @Override
        public int getCount() {
            return seoulEventListData.size();
        }

        @Override
        public Object getItem(int position) {
            return seoulEventListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(String eventImage, String eventName, String eventDate, String eventPlace) {
            Seller_News_EventList addInfo = null;
            addInfo = new Seller_News_EventList();
            addInfo.eventImage = eventImage;
            // 이거 drawable로 안하고 url로 바로 넣으면 이미지 등록되는 거 써서 String으로 바꿨당


            addInfo.eventName = eventName; // 이벤트 명
            addInfo.eventDate = eventDate; // 이벤트 날짜
            addInfo.eventPlace = eventPlace; // 이밴트 장소

            seoulEventListData.add(addInfo);
        }

        public void remove(int position) {
            seoulEventListData.remove(position);
            dataChange();
        }

        public void dataChange() {
            seoulEventAdapter.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder seoulEventHolder;
            if (convertView == null) {
                seoulEventHolder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) seoulEventContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.seller_tab3_news_customlayout, null);

                seoulEventHolder.eventImage = (ImageView) convertView.findViewById(R.id.seller_event_image);
                seoulEventHolder.eventName = (TextView) convertView.findViewById(R.id.seller_event_name);
                seoulEventHolder.eventDate = (TextView) convertView.findViewById(R.id.seller_event_date);
                seoulEventHolder.eventPlace = (TextView) convertView.findViewById(R.id.seller_event_place);

                convertView.setTag(seoulEventHolder);
            } else {
                seoulEventHolder = (ViewHolder) convertView.getTag();
            }

            Seller_News_EventList seoulEventData = seoulEventListData.get(position);

            if (seoulEventData.eventImage != null && seoulEventData.eventImage.compareTo("Please Default Image (E)") != 0) {

                seoulEventHolder.eventImage.setVisibility(View.VISIBLE);
                aq.id(seoulEventHolder.eventImage).image(seoulEventData.eventImage);
                // url로 이미지 바로 때려박기



            } else {
                seoulEventHolder.eventImage.setVisibility(View.GONE);
            }

            seoulEventHolder.eventName.setText(seoulEventData.eventName);
            seoulEventHolder.eventDate.setText(seoulEventData.eventDate);
            seoulEventHolder.eventPlace.setText(seoulEventData.eventPlace);
            return convertView;
        }


    }
    // 비동기식 스레드의 필요성 : 파싱해서 리스트뷰 때려박을 때 사용
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public class FestAsync extends AsyncTask<String, String, ArrayList<Seller_News_EventFull>> {
        // 마지막에 ArrayList<Seller_News_EventFull>>이라는 애가 결과값으로 (파싱의 결과값) 나옴
        protected ArrayList<Seller_News_EventFull> doInBackground(String... params){
            return parser.connectFest();    // 파싱하기
        }
        protected void onPostExecute(ArrayList<Seller_News_EventFull> result){
            seoulEventAdapter = new ListViewAdapter(getActivity()); // 아답터를 받아와서
            seoulEventListView.setAdapter(seoulEventAdapter);   //설정하기
            for(int i=0;i<result.size();i++) {
                seoulEventAdapter.addItem(result.get(i).getMainIMGURL(), result.get(i).getTitle(),
                        result.get(i).getStartDate() + result.get(i).getEndDate(), result.get(i).getPlace());
            }   // 리스트뷰에 박기

        }
    }

}
