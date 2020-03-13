package com.rifaikuci.myapplication;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;


public class Adapter extends PagerAdapter {

    private List<ModelDuyurular> modelDuyurulars;
    private LayoutInflater layoutInflater;
    private Context context;
    ImageView duyuruResim;
    TextView duyuruBaslik,duyuruDetay;
    View view;


    public Adapter(List<ModelDuyurular> modelDuyurulars, Context context) {
        this.modelDuyurulars = modelDuyurulars;
        this.context = context;
    }



    @Override
    public int getCount() {
        return modelDuyurulars.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);

        view= layoutInflater.inflate(R.layout.item,container,false);

        variableDesc();

        Picasso.get().load(modelDuyurulars.get(position).getDuyuruResim()).into(duyuruResim);
        duyuruBaslik.setText(modelDuyurulars.get(position).getDuyuruBaslik());
        duyuruDetay.setText(Html.fromHtml(modelDuyurulars.get(position).getDuyuruDetay()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DuyurularDetay.class);
                intent.putExtra("baslik", modelDuyurulars.get(position).getDuyuruBaslik());
                intent.putExtra("detay", modelDuyurulars.get(position).getDuyuruDetay());
                intent.putExtra("resim", modelDuyurulars.get(position).getDuyuruResim());
                context.startActivity(intent);

            }
        });

        container.addView(view,0);

        return view;
    }


    private void variableDesc() {



        duyuruResim = view.findViewById(R.id.image);
        duyuruBaslik  = view.findViewById(R.id.title);
        duyuruDetay = view.findViewById(R.id.desc);

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
