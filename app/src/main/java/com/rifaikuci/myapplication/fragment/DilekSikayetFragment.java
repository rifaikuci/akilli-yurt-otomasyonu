package com.rifaikuci.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rifaikuci.myapplication.R;

import static com.rifaikuci.myapplication.R.layout.fragment_dilek_sikayet;
import static com.rifaikuci.myapplication.R.layout.fragment_duyurular;

public class DilekSikayetFragment extends Fragment {

    EditText eAdsoyad,eMail,eOda,eDetay;
    Button btnDevam;
    View rootview;

    String adSoyad,mail,istek,tur;
    int odaNumara,radioTurId;


    RadioGroup groupTur;
    RadioButton radioDilek,radioSikayet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(fragment_dilek_sikayet, container, false);
        variableDesc();


       btnDevam.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            adSoyad = eAdsoyad.getText().toString().trim();
            mail = eMail.getText().toString().trim();
            istek = eDetay.getText().toString().trim();
            try {
                odaNumara = Integer.parseInt(eOda.getText().toString().trim());
            }catch (Exception e){
                odaNumara =0;
            }

               radioTurId = groupTur.getCheckedRadioButtonId();
               radioDilek = (RadioButton) rootview.findViewById(radioTurId);

               try { tur = radioDilek.getText().toString().trim(); }
               catch (Exception e){ tur=""; }

               if(adSoyad.isEmpty()) {
                   eAdsoyad.setError("Ad Soyad Giriniz!!!");
               }else if(mail.isEmpty()){
                   eMail.setError("Mail Adresi Giriniz!!!");
               }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                   eMail.setError("Mail Hatalı girilmiştir.!!!");
               } else if (odaNumara ==0){
                   eOda.setError("Oda Numarası Giriniz!!!");}
               else if((550<odaNumara) || (odaNumara<100) ){
                   eOda.setError("Oda Numarası Hatalı girilmiştir!!!");}
               else if(istek.isEmpty()){
                   eDetay.setError("isteklerinizi Giriniz!!!");
               }else if (tur.isEmpty()){
                   Toast.makeText(rootview.getContext(), "Türü giriniz!!!", Toast.LENGTH_SHORT).show();
               }
                else {
                        System.out.println("Bilgiler doğru girilmiştir.");
               }
               }



       });


        return  rootview;

    }

    private void variableDesc() {

        eAdsoyad = (EditText) rootview.findViewById(R.id.eAdsoyad);
        eMail = (EditText) rootview.findViewById(R.id.eMail);
        eOda = (EditText) rootview.findViewById(R.id.eOda);
        eDetay = (EditText) rootview.findViewById(R.id.eDetay);

        groupTur = (RadioGroup) rootview.findViewById(R.id.groupTur);

        radioDilek = (RadioButton) rootview.findViewById(R.id.radioDilek);
        radioSikayet = (RadioButton) rootview.findViewById(R.id.radioSikayet);

        btnDevam = (Button) rootview.findViewById(R.id.btnDevam);
    }
}
