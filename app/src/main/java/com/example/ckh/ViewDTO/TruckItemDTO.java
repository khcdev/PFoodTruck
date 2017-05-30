package com.example.ckh.viewdto;

import android.graphics.Bitmap;

/**
 * Created by Ckh on 2016-10-28.
 */

public class TruckItemDTO {
    private int truck_id;
    private Bitmap truckimg;
    private String imgcode;
    private String truckname;
    private String truck_noti;
    private double truckscore;
    private int truckfavor;


    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public Bitmap getTruckimg() {
        return truckimg;
    }

    public void setTruckimg(Bitmap truckimg) {
        this.truckimg = truckimg;
    }

    public String getImgcode() {
        return imgcode;
    }

    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }

    public String getTruckname() {
        return truckname;
    }

    public void setTruckname(String truckname) {
        this.truckname = truckname;
    }

    public String getTruck_noti() {
        return truck_noti;
    }

    public void setTruck_noti(String truck_noti) {
        this.truck_noti = truck_noti;
    }

    public double getTruckscore() {
        return truckscore;
    }

    public void setTruckscore(double truckscore) {
        this.truckscore = truckscore;
    }

    public int getTruckfavor() {
        return truckfavor;
    }

    public void setTruckfavor(int truckfavor) {
        this.truckfavor = truckfavor;
    }
}
