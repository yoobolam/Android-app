package com.example.bolam.sayusigan.six_mapactivity;

/**
 * Created by bolam on 2018-05-22.
 */

public class Map {
    private  String mstore;
    private  String mwork1;
    private  String mwork2;
    private  String mwork3;
    private  String mtime1;
    private  String mtime2;
    private  String mtime3;

    public Map(String mstore, String mwork1, String mwork2, String mwork3, String mtime1, String mtime2 , String mtime3) {
        this.mstore = mstore;
        this.mwork1 = mwork1;
        this.mwork2= mwork2;
        this.mwork3= mwork3;
        this.mtime1= mtime1;
        this.mtime2= mtime2;
        this.mtime3= mtime3;

    }

    public String getmstore() {
        return mstore;
    }

    public String getmwork1() {return mwork1;}

    public String getmwork2() {return mwork2;}

    public String getmwork3() {return mwork3;}

    public String getmtime1() {
        return mtime1;
    }

    public String getmtime2() {
        return mtime2;
    }

    public String getmtime3() {
        return mtime3;
    }

    public void setmstore(String mstore) {
        this.mstore = mstore;
    }

    public void setmwork1(String mwork1) {
        this.mwork1 = mwork1;
    }

    public void setmwork2(String mwork2) {this.mwork2 = mwork2;}

    public void setmwork3(String mwork3) {
        this.mwork3 = mwork3;
    }

    public void setmtime1(String mtime1) {
        this.mtime1 = mtime1;
    }

    public void setmtime2(String mtime2) {
        this.mtime2 = mtime2;
    }

    public void setmtime3(String mtime3) {
        this.mtime3 = mtime3;
    }

}