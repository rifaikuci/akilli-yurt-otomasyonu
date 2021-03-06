package com.rifaikuci.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.rifaikuci.myapplication.fragment.DilekSikayetFragment;
import com.rifaikuci.myapplication.fragment.DuyurularFragment;
import com.rifaikuci.myapplication.fragment.ProfileFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transparanEkran() ;
        variableDesc();


        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        if(savedInstanceState ==null) {
            // başlangıç olarak gelecek olan fragment
            // fragment açılış ekranı belirler
            try{

            if (getIntent().getStringExtra("tur").equalsIgnoreCase("duyurular")) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new DuyurularFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_duyurular);
            } }
            catch (Exception e){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new DuyurularFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_duyurular);
            }

        }
    }

    private void variableDesc() {

        toolbar = findViewById(R.id.toolbar);

        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

    }

    //Drawer open açılıp-kapanma durumu
    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){ drawer.closeDrawer(GravityCompat.START); }

        else { super.onBackPressed(); }
    }

    //açılır menü seçilme durumları
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_duyurular:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DuyurularFragment()).commit();
                break;
            case R.id.nav_istekler:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DilekSikayetFragment()).commit();
                break;

            case R.id.nav_profile:
                Toast.makeText(getApplicationContext(),"Yapım Aşamasında ",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Uygulama Linki: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void transparanEkran() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
