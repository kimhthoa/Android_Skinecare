package com.example.thesameskincare.db;

public class db_GioHang {
    private int idSanpham;
    private String image;
    private String tensanpham;
    private long gia;
    private int soluong;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getIdSanpham() {
        return idSanpham;
    }

    public void setIdSanpham(int idSanpham) {
        this.idSanpham = idSanpham;
    }

    public db_GioHang(int idSanpham, String image, String tensanpham, long gia, int soluong) {
        this.idSanpham = idSanpham;
        this.image = image;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.soluong = soluong;
    }
}
