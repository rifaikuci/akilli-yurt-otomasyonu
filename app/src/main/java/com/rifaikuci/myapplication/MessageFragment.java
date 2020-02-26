package com.rifaikuci.myapplication;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.List;

import static com.rifaikuci.myapplication.R.id.viewPager;
import static com.rifaikuci.myapplication.R.layout.fragment_chat;
import static com.rifaikuci.myapplication.R.layout.fragment_message;

public class MessageFragment extends Fragment {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    int deneme =0;
    Button btnDetay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(fragment_message,
                container, false);
        models = new ArrayList<>();
        models.add(new Model("https://lh3.googleusercontent.com/proxy/R1Kmv2_ApPpXwnKAABSUDHA7_nx3eo6Y_EKgNtyyU1zMrZAD5vYVS3Q2BAC9v_kxOjBpsRGANCDGPkbhzutX9rGHo-VpjSNMagPe7Z6DIHjG8P8sKxWeKHO0JcMJCK4W_X4w064cvbXz", "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new Model("https://i2.milimaj.com/i/milliyet/75/0x410/5d496a4955428423203a5e04.jpg", "deneme", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new Model("https://i2.milimaj.com/i/milliyet/75/0x410/5d496a4955428423203a5e04.jpg", "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new Model("https://i2.milimaj.com/i/milliyet/75/0x410/5d496a4955428423203a5e04.jpg", "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));


        adapter = new Adapter(models, this.getContext());
        btnDetay = rootview.findViewById(R.id.btnDetay);
        viewPager =(ViewPager) rootview.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2)
        };

        btnDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(models.get(deneme).getTitle().toString());
            }
        });
        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                deneme =position;
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






        return rootview;
    }
}
