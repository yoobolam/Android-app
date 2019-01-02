package com.example.bolam.sayusigan.five_recentfragment;

/**
 * Created by B108 on 2018-03-28.
 */

public class rcntlist {

    private String wTime1;
    private String wTime2;
    private String wTime3;
    private String wStore;
    private String wWork1;
    private String wWork2;
    private String wWork3;
    public rcntlist(String wTime1, String wTime2, String wTime3, String wStore,String wWork1, String wWork2, String wWork3) {

        this.wTime1 = wTime1;
        this.wTime1 = wTime2;
        this.wTime1 = wTime3;
        this.wStore= wStore;
        this.wWork1=wWork1;
        this.wWork2=wWork2;
        this.wWork3=wWork3;
    }

    public String getwStore() {
        return wStore;
    }

    public String getwTime1() {
        return wTime1;
    }
    public String getwTime2() {
        return wTime2;
    }
    public String getwTime3() {
        return wTime3;
    }

    public String getwWork1(){return wWork1;}
    public String getwWork2(){return wWork2;}
    public String getwWork3(){return wWork3;}

    public void setwTime1(String wTime1) {
        this.wTime1 = wTime1;
    }
    public void setwTime2(String wTime2) {
        this.wTime2 = wTime2;
    }
    public void setwTime3(String wTime3) {
        this.wTime3 = wTime3;
    }

    public void setwStore(String wStore) {
        this.wStore = wStore;
    }

    public void setwWork1(String wWork1) {this.wWork1 = wWork1; }
    public void setwWork2(String wWork2) {this.wWork2= wWork2; }
    public void setwWork3(String wWork3) {this.wWork3 = wWork3; }
}
