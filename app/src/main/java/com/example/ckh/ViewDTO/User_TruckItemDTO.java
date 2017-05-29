package com.example.ckh.ViewDTO;

import android.graphics.drawable.Drawable;

/**
 * Created by Ckh on 2016-10-02.
 * modified by kalin
 */
public class User_TruckItemDTO {
    private Drawable TruckImg;
    private String TruckTitle;
    private String TruckInfo;
    private String TruckLoc;

    public Drawable getTruckImg() {
        return TruckImg;
    }

    public void setTruckImg(Drawable truckImg) {
        TruckImg = truckImg;
    }

    public String getTruckTitle() {
        return TruckTitle;
    }

    public void setTruckTitle(String truckTitle) {
        TruckTitle = truckTitle;
    }

    public String getTruckInfo() {
        return TruckInfo;
    }

    public void setTruckInfo(String truckInfo) {
        TruckInfo = truckInfo;
    }

    public String getTruckLoc() {
        return TruckLoc;
    }

    public void setTruckLoc(String truckLoc) {
        TruckLoc = truckLoc;
    }

}
