package com.bkstudios.marvelapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bkstudios.marvelapp.databinding.MyprofileBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.ArrayList;

public class MyProfile extends AppCompatActivity implements Serializable {
    MyprofileBinding binding = null;
    ArrayList<String> tabs = new ArrayList<>();

    String ownerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MyProfile.this,R.layout.myprofile);
        Bundle bundle=getIntent().getExtras();

        ownerImage=bundle.getString("ownerImage");
        String ownerName=bundle.getString("ownerName");



        ArrayList<Repos> repos= (ArrayList<Repos>) bundle.getSerializable("Array");
        Glide.with(MyProfile.this)
                .load(ownerImage)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.img);

        binding.name.setText(ownerName);

//        Bundle bundle1=bundle.getBundle("Array");


        tabs.add("MY REPOS");
        tabs.add("FAVOURITES");

        ViewPager pager = findViewById(R.id.viewer);

        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(),tabs,repos);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProfile.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }


}