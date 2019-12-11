package com.example.thesameskincare.db;

import java.io.Serializable;

public class db_SanPham implements Serializable {
    private int maSanPham;
    private String tenSanPham;
    private String thuonghieu;
    private int dungtich;
    private String mota;
    private int dongia;
    private int soluongcosan;
    private int phivanchuyen;
    private String muc;
    private String anh;
    private int maloai;
    private int mashop;

    public db_SanPham(int maSanPham, String tenSanPham, String thuonghieu, int dungtich, String mota, int dongia, int soluongcosan, int phivanchuyen, String muc, String anh, int maloai, int mashop) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.thuonghieu = thuonghieu;
        this.dungtich = dungtich;
        this.mota = mota;
        this.dongia = dongia;
        this.soluongcosan = soluongcosan;
        this.phivanchuyen = phivanchuyen;
        this.muc = muc;
        this.anh = anh;
        this.maloai = maloai;
        this.mashop = mashop;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public int getDungtich() {
        return dungtich;
    }

    public void setDungtich(int dungtich) {
        this.dungtich = dungtich;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluongcosan() {
        return soluongcosan;
    }

    public void setSoluongcosan(int soluongcosan) {
        this.soluongcosan = soluongcosan;
    }

    public int getPhivanchuyen() {
        return phivanchuyen;
    }

    public void setPhivanchuyen(int phivanchuyen) {
        this.phivanchuyen = phivanchuyen;
    }

    public String getMuc() {
        return muc;
    }

    public void setMuc(String muc) {
        this.muc = muc;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getMashop() {
        return mashop;
    }

    public void setMashop(int mashop) {
        this.mashop = mashop;
    }
}
