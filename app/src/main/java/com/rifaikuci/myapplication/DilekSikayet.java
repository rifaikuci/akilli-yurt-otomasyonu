package com.rifaikuci.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DilekSikayet extends AppCompatActivity {

    EditText eAdsoyad,eMail,eSifre,eDetay;
    Button btnDevam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilek_sikayet);
        variableDesc();
    }

    private void variableDesc() {

        eAdsoyad = (EditText) findViewById(R.id.eAdsoyad);
        eMail = (EditText) findViewById(R.id.eMail);
        eSifre = (EditText) findViewById(R.id.eSifre);
        eDetay = (EditText) findViewById(R.id.eDetay);

        btnDevam = (Button) findViewById(R.id.btnDevam);
    }
}
