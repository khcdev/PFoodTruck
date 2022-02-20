package com.hungryduck.viewmodel;

import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by kalin on 2017-05-28.
 */

public class ItemViewHolderDTO {
    private TextView writer;
    private RatingBar score;
    private TextView date;
    private TextView contents;


    public TextView getWriter() {
        return writer;
    }

    public void setWriter(TextView writer) {
        this.writer = writer;
    }

    public RatingBar getScore() {
        return score;
    }

    public void setScore(RatingBar score) {
        this.score = score;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getContents() {
        return contents;
    }

    public void setContents(TextView contents) {
        this.contents = contents;
    }
}
