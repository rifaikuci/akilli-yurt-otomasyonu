package com.rifaikuci.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class DuyurularDetay extends YouTubeBaseActivity {

    ImageView imageBack,image;
    TextView txtDetail,txtBaslik;
    Intent intent;
    String baslik,detay,resim,video;

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyurular_detay);
        variableDesc();

        txtBaslik.setText("Hata !!!");
        txtDetail.setText("İnternet Olmayabilir !!!");
        image.setImageResource(R.drawable.custom_background);


        try {
            baslik = getIntent().getStringExtra("duyuruBaslik");
            detay  = getIntent().getStringExtra("duyuruDetay");
            resim  = getIntent().getStringExtra("duyuruResim");
            video  = getIntent().getStringExtra("duyuruVideo");

            if(video.isEmpty()){
                youTubePlayerView.setVisibility(View.INVISIBLE);
            }else
            {
                youTubePlayerView.setVisibility(View.VISIBLE);
                        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(video);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            youTubePlayerView.setVisibility(View.INVISIBLE);
                    }

                };

                youTubePlayerView.initialize("AIzaSyAWjdszBt7aVAYMT8XX_pOuk5TQLLEjPS4",onInitializedListener);
            }

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

    youTubePlayerView =findViewById(R.id.youtubeview);

    }
}
