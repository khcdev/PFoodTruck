package com.example.ckh.ViewDTO;

import android.graphics.drawable.Drawable;

/**
 * Created by Ckh on 2016-10-30.
 */
public class FavorTruckDTO {
    private Drawable truckImg;
    private int truck_id;
    private String truckName;

    public Drawable getTruckImg() {
        return truckImg;
    }

    public void setTruckImg(Drawable truckImg) {
        this.truckImg = truckImg;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }
}
