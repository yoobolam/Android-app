package com.example.bolam.sayusigan;

/**
 * Created by bolam on 2018-05-17.
 */

public class ImeiData {
    private static ImeiData Instace = null;
    String imei = null;

    public static ImeiData getInstace() {
        if(Instace == null){
            Instace = new ImeiData();
        }
        return Instace;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}


