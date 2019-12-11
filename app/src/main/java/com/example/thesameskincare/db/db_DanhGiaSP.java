package com.example.thesameskincare.db;

import java.util.Date;

public class db_DanhGiaSP {
    private int imagaUser;
    private String ten;
    private String content;
    private String getDay;

    public db_DanhGiaSP(int imagaUser, String ten, String content, String getDay) {
        this.imagaUser = imagaUser;
        this.ten = ten;
        this.content = content;
        this.getDay = getDay;
    }

    public int getImagaUser() {
        return imagaUser;
    }

    public void setImagaUser(int imagaUser) {
        this.imagaUser = imagaUser;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGetDay() {
        return getDay;
    }

    public void setGetDay(String getDay) {
        this.getDay = getDay;
    }
}
