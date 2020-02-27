package com.rifaikuci.myapplication;

public class ModelDuyurular {

    private String duyuruBaslik;
    private String duyuruDetay;
    private String duyuruResim;

    public ModelDuyurular(String duyuruBaslik, String duyuruDetay, String duyuruResim) {
        this.duyuruBaslik = duyuruBaslik;
        this.duyuruDetay = duyuruDetay;
        this.duyuruResim = duyuruResim;
    }

    public String getDuyuruBaslik() {
        return duyuruBaslik;
    }

    public void setDuyuruBaslik(String duyuruBaslik) {
        this.duyuruBaslik = duyuruBaslik;
    }

    public String getDuyuruDetay() {
        return duyuruDetay;
    }

    public void setDuyuruDetay(String duyuruDetay) {
        this.duyuruDetay = duyuruDetay;
    }

    public String getDuyuruResim() {
        return duyuruResim;
    }

    public void setDuyuruResim(String duyuruResim) {
        this.duyuruResim = duyuruResim;
    }
}
