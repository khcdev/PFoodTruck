package com.hungryduck.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hungryduck.foodtruck.R;
import com.hungryduck.viewmodel.ItemViewHolderDTO;
import com.hungryduck.viewmodel.ReviewItemDTO;

import java.util.ArrayList;

/**
 * Created by Ckh on 2016-10-27.
 */
public class SellerReviewListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ReviewItemDTO> ReviewItemListData = new ArrayList<>();

    public SellerReviewListViewAdapter(Context allMenuContext) {
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
        ItemViewHolderDTO ItemHolder;
        if (convertView == null) {
            ItemHolder = new ItemViewHolderDTO();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reviewformlayout, null);

            ItemHolder.setWriter((TextView) convertView.findViewById(R.id.reviewform_writer));
            ItemHolder.setDate((TextView) convertView.findViewById(R.id.reviewform_data));
            ItemHolder.setScore((RatingBar) convertView.findViewById(R.id.reviewrating));
            ItemHolder.setContents((TextView) convertView.findViewById(R.id.reviewform_content));

            convertView.setTag(ItemHolder);
        } else {
            ItemHolder = (ItemViewHolderDTO) convertView.getTag();
        }
        ReviewItemDTO RevItemData = ReviewItemListData.get(position);


        ItemHolder.getWriter().setText(RevItemData.getWriter());
        ItemHolder.getDate().setText(RevItemData.getDate());
        ItemHolder.getScore().setRating((float) RevItemData.getScore());
        ItemHolder.getContents().setText(RevItemData.getContent());


        return convertView;
    }

    public void addItem(String writer, String date, int score, String contents) {
        ReviewItemDTO addingData = new ReviewItemDTO();
        addingData.setWriter(writer);
        addingData.setDate(date);
        addingData.setScore(score);
        addingData.setContent(contents);
        ReviewItemListData.add(addingData);
    }

    public void remove(int position) {
        ReviewItemListData.remove(position);
    }
//reviewrating

//    ItemViewHolderDTO로 재정의
//    private class ViewHolder{
//        public TextView writer;
//        public RatingBar score;
//        public TextView date;
//        public TextView contents;
//    }
}
