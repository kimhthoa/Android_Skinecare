package com.example.thesameskincare.db;

import java.io.Serializable;

public class db_User implements Serializable {
    private int maUser;
    private String TenUser;
    private String matKhau;
    private String anh;
    private String hoten;
    private String gioiTinh;
    private String diachi;
    private String sdt;
    private String email;

    public db_User(int maUser, String tenUser, String matKhau,String anh, String hoten, String gioiTinh, String diachi, String sdt, String email) {
        this.maUser = maUser;
        TenUser = tenUser;
        this.matKhau = matKhau;
        this.anh = anh;
        this.hoten = hoten;
        this.gioiTinh = gioiTinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getTenUser() {
        return TenUser;
    }

    public void setTenUser(String tenUser) {
        TenUser = tenUser;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
