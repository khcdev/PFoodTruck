package com.example.ckh.ViewDTO;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kalin on 2017-05-29.
 */

public class FavorListViewAdapterDTO {
    private ImageView img;
    private TextView name;
    private LinearLayout layout;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }
}
