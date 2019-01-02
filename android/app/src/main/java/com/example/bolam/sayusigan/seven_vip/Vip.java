package com.example.bolam.sayusigan.seven_vip;

/**
 * Created by B108 on 2018-03-28.
 */

public class Vip {

    private  String vstore;
    private  String vwork1;
    private  String vwork2;
    private  String vwork3;
    private  String vtime1;
    private  String vtime2;
    private  String vtime3;

    public Vip(String vstore, String vwork1, String vwork2, String vwork3, String vtime1, String vtime2 , String vtime3) {
        this.vstore = vstore;
        this.vwork1 = vwork1;
        this.vwork2= vwork2;
        this.vwork3= vwork3;
        this.vtime1= vtime1;
        this.vtime2= vtime2;
        this.vtime3= vtime3;

    }

    public String getvstore() {
      return vstore;
    }

    public String getvwork1() {return vwork1;}

    public String getvwork2() {return vwork2;}

    public String getvwork3() {return vwork3;}

    public String getvtime1() {
        return vtime1;
    }

    public String getvtime2() {
        return vtime2;
    }

    public String getvtime3() {
        return vtime3;
    }

    public void setvstore(String vstore) {
        this.vstore = vstore;
    }

    public void setvwork1(String vwork1) {
        this.vwork1 = vwork1;
    }

    public void setvwork2(String vwork2) {
        this.vwork2 = vwork2;
    }

    public void setvwork3(String vwork3) {
        this.vwork3 = vwork3;
    }

    public void setvtime1(String vtime1) {
        this.vtime1 = vtime1;
    }

    public void setvtime2(String vtime2) {
        this.vtime2 = vtime2;
    }

    public void setvtime3(String vtime3) {
        this.vtime3 = vtime3;
    }

    }
