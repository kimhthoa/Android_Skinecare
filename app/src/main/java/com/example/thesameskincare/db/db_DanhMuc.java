package com.example.thesameskincare.db;

public class db_DanhMuc {
    private int maLoai;
    private String tenLoai;
    private String anhsp;

    public db_DanhMuc(int maLoai, String tenLoai, String anhsp) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.anhsp = anhsp;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }
}
