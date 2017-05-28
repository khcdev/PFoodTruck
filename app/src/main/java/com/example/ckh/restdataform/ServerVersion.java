package com.example.ckh.restdataform;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ckj on 2017-05-27.
 */
public class ServerVersion {
    @SerializedName("version")
    int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
