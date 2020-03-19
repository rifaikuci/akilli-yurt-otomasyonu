package com.rifaikuci.myapplication;

public class ModelDuyurular {

    private String duyuruBaslik;
    private String duyuruDetay;
    private String duyuruResim;

    public String getDuyuruVideo() {
        return duyuruVideo;
    }

    public void setDuyuruVideo(String duyuruVideo) {
        this.duyuruVideo = duyuruVideo;
    }

    private String duyuruVideo;

    public ModelDuyurular(String duyuruBaslik, String duyuruDetay, String duyuruResim, String duyuruVideo) {
        this.duyuruBaslik = duyuruBaslik;
        this.duyuruDetay = duyuruDetay;
        this.duyuruResim = duyuruResim;
        this.duyuruVideo = duyuruVideo;
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
