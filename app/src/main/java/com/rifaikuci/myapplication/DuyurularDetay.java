package com.rifaikuci.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DuyurularDetay extends AppCompatActivity {

    ImageView imageBack,image;
    TextView txtDetail,txtBaslik;
    Intent intent;
    String baslik,detay,resim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyurular_detay);
        variableDesc();

        txtBaslik.setText("Hata !!!");
        txtDetail.setText("İnternet Olmayabilir !!!");
        image.setImageResource(R.drawable.custom_background);

        try {
            baslik = getIntent().getStringExtra("baslik");
            detay  = getIntent().getStringExtra("detay");
            resim  = getIntent().getStringExtra("resim");

            txtBaslik.setText(baslik);

            // link verme
            txtDetail.setText(Html.fromHtml(detay));
            txtDetail.setMovementMethod(LinkMovementMethod.getInstance());
            Picasso.get().load(resim).into(image);
        }
        catch (Exception e){ }

        //geri butonu
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBackClick();
            }
        });
    }

    private void imageBackClick() {

      intent = new Intent(getApplicationContext(),MainActivity.class);
      intent.putExtra("tur","duyurular");
      startActivity(intent);

    }

    private void variableDesc() {

    imageBack = (ImageView) findViewById(R.id.imageBack);
    image     = (ImageView) findViewById(R.id.image);

    txtDetail = (TextView) findViewById(R.id.txtDetail);
    txtBaslik = (TextView) findViewById(R.id.txtBaslik);
    }
}
