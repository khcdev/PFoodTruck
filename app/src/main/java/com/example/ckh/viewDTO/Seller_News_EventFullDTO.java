package com.example.ckh.viewDTO;

import java.io.Serializable;

/**
 * Created by HOME on 2016-10-24.
 */
public class Seller_News_EventFullDTO implements Serializable {
    private String title;
    private String startDate;
    private String endDate;
    private String place;
    private String mainIMGURL;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getMainIMGURL() {
        return mainIMGURL;
    }

    public void setMainIMGURL(String mainIMGURL) {
        this.mainIMGURL = mainIMGURL;
    }


}
