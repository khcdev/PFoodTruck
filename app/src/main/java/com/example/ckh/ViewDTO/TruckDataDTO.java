package com.example.ckh.ViewDTO;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kalin on 2017-05-28.
 */

public class TruckDataDTO {
    private ImageView Truckimg;
    private TextView TruckTitle;
    private TextView TruckInfo;
    private TextView TruckLoc;

    public ImageView getTruckimg() {
        return Truckimg;
    }

    public void setTruckimg(ImageView truckimg) {
        Truckimg = truckimg;
    }

    public TextView getTruckTitle() {
        return TruckTitle;
    }

    public void setTruckTitle(TextView truckTitle) {
        TruckTitle = truckTitle;
    }

    public TextView getTruckInfo() {
        return TruckInfo;
    }

    public void setTruckInfo(TextView truckInfo) {
        TruckInfo = truckInfo;
    }

    public TextView getTruckLoc() {
        return TruckLoc;
    }

    public void setTruckLoc(TextView truckLoc) {
        TruckLoc = truckLoc;
    }
}
