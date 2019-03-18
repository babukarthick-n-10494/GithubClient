package com.bkstudios.marvelapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter {
    @NonNull
    Activity activity=null;
    ArrayList<Search> data=null;
    public SearchAdapter(ArrayList<Search> data, Activity activity){
        this.data=data;
        this.activity=activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.searchlist,viewGroup,false);
       SearchHolder holder=new SearchHolder(view,activity);
        return holder;

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SearchHolder) viewHolder).setData(data.get(i));

    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void addData(ArrayList<Search> datas){
        data.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
