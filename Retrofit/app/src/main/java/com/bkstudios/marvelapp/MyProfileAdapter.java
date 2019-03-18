package com.bkstudios.marvelapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity=null;
    ArrayList<Repos> data=null;
    public MyProfileAdapter(ArrayList<Repos> data, Activity activity){
        this.data=data;
        this.activity=activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.fragment_pagerlist,viewGroup,false);
        MyProfileHolder holder=new MyProfileHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((MyProfileHolder) viewHolder).setData(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
