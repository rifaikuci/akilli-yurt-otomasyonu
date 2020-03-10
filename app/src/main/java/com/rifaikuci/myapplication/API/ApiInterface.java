package com.rifaikuci.myapplication.API;

import com.rifaikuci.myapplication.ModelDuyurular;
import com.rifaikuci.myapplication.datas.SikayetData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {



    // Get işlemleri
    @GET("duyurular.php")
    Call<List<ModelDuyurular>> getDuyurular();

    @FormUrlEncoded
    @POST("saveTalep.php")
    Call<SikayetData> saveData(
            @Field("adSoyad") String adSoyad,
            @Field("odaNumara") int odaNumara,
            @Field("istek") String istek,
            @Field("tur") String tur,
            @Field("mail") String mail
    );






}
