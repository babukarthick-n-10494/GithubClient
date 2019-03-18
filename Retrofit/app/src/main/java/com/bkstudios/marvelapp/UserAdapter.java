package com.bkstudios.marvelapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkstudios.marvelapp.databinding.UserprofileBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity=null;
    ArrayList<Repos> data=null;
    UserprofileBinding binding;
    public UserAdapter(ArrayList<Repos> data, Activity activity,UserprofileBinding binding){
        this.data=data;
        this.activity=activity;
        this.binding=binding;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.userprofilelist,viewGroup,false);
        UserHolder holder=new UserHolder(view,activity,binding);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserHolder holder = ((UserHolder) viewHolder);
        holder.setData(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
