package com.example.ckh.cstview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.ckh.foodtruck.R;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-27.
 */
public class SellerReviewListviewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ReviewItem> ReviewItemListData = new ArrayList<ReviewItem>();

    public SellerReviewListviewAdapter(Context allMenuContext) {
        super();
        this.mContext = allMenuContext;
    }

    @Override
    public int getCount() {
        return ReviewItemListData.size();
    }

    @Override
    public Object getItem(int position) {
        return ReviewItemListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder ItemHolder;
        if(convertView == null) {
            ItemHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reviewformlayout, null);
            ItemHolder.writer = (TextView) convertView.findViewById(R.id.reviewform_writer);
            ItemHolder.date = (TextView) convertView.findViewById(R.id.reviewform_data);
            ItemHolder.score = (RatingBar) convertView.findViewById(R.id.reviewrating);
            ItemHolder.contents = (TextView) convertView.findViewById(R.id.reviewform_content);
            convertView.setTag(ItemHolder);
        }else{
            ItemHolder = (ViewHolder)convertView.getTag();
        }
        ReviewItem RevItemData = ReviewItemListData.get(position);

        ItemHolder.writer.setText(RevItemData.writer);
        ItemHolder.date.setText(RevItemData.date);
        ItemHolder.score.setRating(RevItemData.score);
        ItemHolder.contents.setText(RevItemData.content);
        return convertView;
    }
    public void addItem(String writer,String date, int score, String contents){
        ReviewItem addingData = new ReviewItem();
        addingData.writer =writer;
        addingData.date = date;
        addingData.score= score;
        addingData.content=contents;

        ReviewItemListData.add(addingData);
    }
    public void remove(int position){
        ReviewItemListData.remove(position);
    }
//reviewrating
    private class ViewHolder{
        public TextView writer;
        public RatingBar score;
        public TextView date;
        public TextView contents;
    }
}
//팀뷰 종료 안할테니 나 분리 수거 하러 갈거니간 수정할거 있음 다 해
// 예 제꺼에서 먼저 실험하고 완성되면 와서 할께용
// 깃추가 꼭 해