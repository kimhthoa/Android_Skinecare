package com.example.thesameskincare.db;

public class db_shop {
    private int mashop;
    private String tenshop;
    private String diachi;
    private String sdt;

    public db_shop(int mashop, String tenshop, String diachi, String sdt) {
        this.mashop = mashop;
        this.tenshop = tenshop;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getMashop() {
        return mashop;
    }

    public void setMashop(int mashop) {
        this.mashop = mashop;
    }

    public String getTenshop() {
        return tenshop;
    }

    public void setTenshop(String tenshop) {
        this.tenshop = tenshop;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
