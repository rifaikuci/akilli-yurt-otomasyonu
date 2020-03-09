package com.rifaikuci.myapplication.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SikayetData {


    @Expose
    @SerializedName("adSoyad") private String adSoyad;
    @Expose
    @SerializedName("mail") private String mail;
    @Expose
    @SerializedName("turResim") private String turResim;
    @Expose
    @SerializedName("turEnlem") private Double turEnlem;
    @Expose
    @SerializedName("turBoylam") private Double turBoylam;
    @Expose
    @SerializedName("tur") private String tur;
    @Expose
    @SerializedName("durum") private String durum;
}
