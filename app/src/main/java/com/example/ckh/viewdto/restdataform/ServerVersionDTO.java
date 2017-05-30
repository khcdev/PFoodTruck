package com.example.ckh.viewdto.restdataform;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ckj on 2017-05-27.
 */
public class ServerVersionDTO {
    @SerializedName("version")
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
