package com.hungryduck.viewmodel;

import android.graphics.Bitmap;

/**
 * Created by Ckh on 2016-10-02.
 */
public class SellerMenuItemDTO {
    private Bitmap MenuImage;
    private String MenuTitle;
    private String Price;
    private String Origin;
    private String Info;

    public Bitmap getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(Bitmap menuImage) {
        MenuImage = menuImage;
    }

    public String getMenuTitle() {
        return MenuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        MenuTitle = menuTitle;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

}
