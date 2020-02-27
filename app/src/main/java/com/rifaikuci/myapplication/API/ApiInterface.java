package com.rifaikuci.myapplication.API;

import com.rifaikuci.myapplication.ModelDuyurular;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {



    // Get işlemleri
    @GET("duyurular.php")
    Call<List<ModelDuyurular>> getDuyurular();






}
