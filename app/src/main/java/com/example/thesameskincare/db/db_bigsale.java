package com.example.thesameskincare.db;

public class db_bigsale {
    private int img_bigsale;
    private String mota;

    public db_bigsale(int img_bigsale, String mota) {
        this.img_bigsale = img_bigsale;
        this.mota = mota;
    }

    public int getImg_bigsale() {
        return img_bigsale;
    }

    public void setImg_bigsale(int img_bigsale) {
        this.img_bigsale = img_bigsale;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
