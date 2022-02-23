package com.hungryduck.viewmodel.restdataform;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ckj on 2017-05-27.
 */
public class SpotInformDTO implements Serializable {
    @SerializedName("SPOT_ID")
    private int SPOT_ID;
    @SerializedName("MALE")
    private int MALE;
    @SerializedName("FEMALE")
    private int FEMALE;
    @SerializedName("TWYO_BELOW")
    private int TWYO_BELOW;
    @SerializedName("TWNT_THRTS")
    private int TWNT_THRTS;
    @SerializedName("FRTS_FFTS")
    private int FRTS_FFTS;
    @SerializedName("SXTS_ABOVE")
    private int SXTS_ABOVE;
    @SerializedName("SPOT_NAME")
    private String SPOT_NAME;
    @SerializedName("X_POS")
    private double X_POS;
    @SerializedName("Y_POS")
    private double Y_POS;
    @SerializedName("GU_ID")
    private int GU_ID;
    @SerializedName("GU_NAME")
    private String GU_NAME;

    public int getSPOT_ID() {
        return SPOT_ID;
    }

    public int getMALE() {
        return MALE;
    }

    public int getFEMALE() {
        return FEMALE;
    }

    public int getTWYO_BELOW() {
        return TWYO_BELOW;
    }

    public int getTWNT_THRTS() {
        return TWNT_THRTS;
    }

    public int getFRTS_FFTS() {
        return FRTS_FFTS;
    }

    public int getSXTS_ABOVE() {
        return SXTS_ABOVE;
    }

    public String getSPOT_NAME() {
        return SPOT_NAME;
    }

    public double getX_POS() {
        return X_POS;
    }

    public double getY_POS() {
        return Y_POS;
    }

    public int getGU_ID() {
        return GU_ID;
    }

    public String getGU_NAME() {
        return GU_NAME;
    }

    public void setSPOT_ID(int SPOT_ID) {
        this.SPOT_ID = SPOT_ID;
    }

    public void setMALE(int MALE) {
        this.MALE = MALE;
    }

    public void setFEMALE(int FEMALE) {
        this.FEMALE = FEMALE;
    }

    public void setTWYO_BELOW(int TWYO_BELOW) {
        this.TWYO_BELOW = TWYO_BELOW;
    }

    public void setTWNT_THRTS(int TWNT_THRTS) {
        this.TWNT_THRTS = TWNT_THRTS;
    }

    public void setFRTS_FFTS(int FRTS_FFTS) {
        this.FRTS_FFTS = FRTS_FFTS;
    }

    public void setSXTS_ABOVE(int SXTS_ABOVE) {
        this.SXTS_ABOVE = SXTS_ABOVE;
    }

    public void setSPOT_NAME(String SPOT_NAME) {
        this.SPOT_NAME = SPOT_NAME;
    }

    public void setX_POS(double x_POS) {
        X_POS = x_POS;
    }

    public void setY_POS(double y_POS) {
        Y_POS = y_POS;
    }

    public void setGU_ID(int GU_ID) {
        this.GU_ID = GU_ID;
    }

    public void setGU_NAME(String GU_NAME) {
        this.GU_NAME = GU_NAME;
    }
}
