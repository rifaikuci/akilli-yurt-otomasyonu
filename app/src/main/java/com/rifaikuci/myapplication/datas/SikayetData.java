package com.rifaikuci.myapplication.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SikayetData {


    @Expose
    @SerializedName("adSoyad") private String adSoyad;
    @Expose
    @SerializedName("odaNumara") private int odaNumara;
    @Expose
    @SerializedName("istek") private String istek;
    @Expose
    @SerializedName("tur") private String tur;
    @Expose
    @SerializedName("mail") private String mail;
    @Expose
    @SerializedName("success") private boolean success = false;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SikayetData(String adSoyad, int odaNumara, String istek, String tur, String mail) {
        this.adSoyad = adSoyad;
        this.odaNumara = odaNumara;
        this.istek = istek;
        this.tur = tur;
        this.mail = mail;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getOdaNumara() {
        return odaNumara;
    }

    public void setOdaNumara(int odaNumara) {
        this.odaNumara = odaNumara;
    }

    public String getIstek() {
        return istek;
    }

    public void setIstek(String istek) {
        this.istek = istek;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

