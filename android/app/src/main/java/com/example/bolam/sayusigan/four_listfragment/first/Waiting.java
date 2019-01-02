package com.example.bolam.sayusigan.four_listfragment.first;

/**
 * Created by B108 on 2018-03-28.
 */

public class Waiting {

    private int wOrder;
    private String wTime;
    private String wStore;
    private String wWork;
    private String eOrder;


    public Waiting(int wOrder, String wTime, String wStore, String wWork, String eOrder) {
        this.wOrder = wOrder;
        this.wTime = wTime;
        this.wStore= wStore;
        this.wWork= wWork;
        this.eOrder=eOrder;
    }

    public String getwStore() {
      return wStore;
    }

    public String getwWork() {

        return wWork;
    }

    public int getwOrder() {
        return wOrder;
    }

    public String getwTime() {
        return wTime;
    }

    public String geteOrder() {return eOrder;}

    public void setwOrder(int wOrder) {
        this.wOrder = wOrder;
    }

    public void setwTime(String wTime) {
        this.wTime = wTime;
    }

    public void eOrder(String eOrder) {this.eOrder = eOrder;}

    public void setwWork(String wWork) {
        this.wWork = wWork;
    }

    public void setwStore(String wStore) {
        this.wStore = wStore;
    }



}
