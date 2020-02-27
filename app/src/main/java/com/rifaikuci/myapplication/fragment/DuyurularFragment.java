package com.rifaikuci.myapplication.fragment;

import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;


import com.rifaikuci.myapplication.API.ApiClient;
import com.rifaikuci.myapplication.API.ApiInterface;
import com.rifaikuci.myapplication.Adapter;
import com.rifaikuci.myapplication.ModelDuyurular;
import com.rifaikuci.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rifaikuci.myapplication.R.layout.fragment_duyurular;

public class DuyurularFragment extends Fragment {

    ViewPager viewPager;
    Adapter adapter;
    ProgressDialog progressDialog;
    ApiInterface apiInterface ;
    View rootview;
    SwipeRefreshLayout swipe;

    List<ModelDuyurular> modelDuyurulars,gosterilecek;
    String duyuruBaslik,duyuruDetay,duyuruResim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(fragment_duyurular, container, false);

        variableDesc();

        apiInterface  = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ModelDuyurular>> call= apiInterface.getDuyurular();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiInterface  = ApiClient.getApiClient().create(ApiInterface.class);
                Call<List<ModelDuyurular>> call= apiInterface.getDuyurular();

                listClear();

                progressDialog.show();

                call.enqueue(new Callback<List<ModelDuyurular>>() {
                    @Override
                    public void onResponse(Call<List<ModelDuyurular>> call, Response<List<ModelDuyurular>> response) {

                        progressDialog.dismiss();
                        swipe.setRefreshing(false);

                      onRespo(response);

                       }

                    @Override
                    public void onFailure(Call<List<ModelDuyurular>> call, Throwable t) {
                       onFail();
                    }
                });


            }
        });


        call.enqueue(new Callback<List<ModelDuyurular>>() {
            @Override
            public void onResponse(Call<List<ModelDuyurular>> call, Response<List<ModelDuyurular>> response) {

              onRespo(response);
            }

            @Override
            public void onFailure(Call<List<ModelDuyurular>> call, Throwable t) {
               onFail();
            }
        });


        return rootview;
    }

    private void onRespo(Response<List<ModelDuyurular>> response) {
        if( response.isSuccessful() && response.body() !=null){

            gosterilecek = (ArrayList<ModelDuyurular>) response.body();

            for (int i =0;i<gosterilecek.size();i++){

                duyuruBaslik =gosterilecek.get(i).getDuyuruBaslik();
                duyuruDetay =gosterilecek.get(i).getDuyuruDetay();
                duyuruResim =gosterilecek.get(i).getDuyuruResim();

                modelDuyurulars.add(new ModelDuyurular(duyuruBaslik,duyuruDetay,duyuruResim));
            }

            adapter = new Adapter(modelDuyurulars,rootview.getContext());
            viewPager =(ViewPager) rootview.findViewById(R.id.viewPager);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(130, 0, 130, 0);

        }
    }


    private void onFail() {

        progressDialog.dismiss();
        Toast.makeText(rootview.getContext(),"internet bağlantınızı kontrol ediniz!!!",Toast.LENGTH_SHORT).show();
    }

    private void listClear() {

        gosterilecek.clear();
        modelDuyurulars.clear();
    }

    private  void variableDesc(){

        swipe = rootview.findViewById(R.id.swipe);

        modelDuyurulars = new ArrayList<>();
        gosterilecek = new ArrayList<>();

        progressDialog = new ProgressDialog(rootview.getContext());
        progressDialog.setMessage("Lütfen Bekleyiniz...");


    }
}
