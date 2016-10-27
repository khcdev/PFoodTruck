package com.example.ckh.foodtruck.seller;

import java.io.Serializable;

/**
 * Created by HOME on 2016-09-22.
 */
public class MovingPeople implements Serializable{
    // Serializable로 직렬화 했다. ArrayList에 담기는 객체를 직렬화 해야 인텐트에 전달시킬 수 있음
    public MovingPeople(String examin_spot_cd, int male, int female, int twyoBelow, int twnt_thrts, int frts_ffts,
                        int sxts_above, String examin_spot_name, double Xcode, double Ycode, String guCode, String guname) {

        this.examin_spot_cd = examin_spot_cd;
        this.examin_spot_name = examin_spot_name;
        this.male = male;
        this.female = female;
        this.twyoBelow = twyoBelow;
        this.twnt_thrts = twnt_thrts;
        this.sxts_above = sxts_above;
        this.frts_ffts = frts_ffts;
        this.Xcode = Xcode;
        this.Ycode = Ycode;
        this.guCode = guCode;
        this.guname = guname;
    }

    String examin_spot_cd;
    public String examin_spot_name;
    public int male;
    public int female;
    public int twyoBelow;
    public int twnt_thrts;
    public int frts_ffts;
    public int sxts_above;
    public double Xcode;
    public double Ycode;
    String guCode;
    public String guname;
    String rank;

    @Override
    public String toString() {

        return "[" + examin_spot_cd + "\t" + male + " " + female + "\t" + twyoBelow + " " + twnt_thrts + " " + frts_ffts
                + " " + sxts_above + "\t" + examin_spot_name + "\t" + Xcode + " " + Ycode + "\t" + guCode + " " + guname
                + "]";

    }

    public String toStringXcode(){
        return ""+Xcode;
    }
    public String toStringYcode(){
        return ""+Ycode;
    }

}