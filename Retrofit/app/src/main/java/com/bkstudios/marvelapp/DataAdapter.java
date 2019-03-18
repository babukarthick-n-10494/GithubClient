package com.bkstudios.marvelapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    ArrayList<Repos> data=null;
    Activity activity;
    DataAdapter adapter;
    public DataAdapter(Activity activity,ArrayList<Repos> data){
        this.activity=activity;
        this.data=data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view =inflater.inflate(R.layout.deletefavour,viewGroup,false);
        DataHolder holder=new DataHolder(view, activity,adapter);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DataHolder dataHolder = (DataHolder) viewHolder;
        final Repos repos = data.get(i);
        dataHolder.setData(repos);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void removeItem(int position) {
        Repos sto = data.get(position);
        data.remove(position);

        DB db = Room.databaseBuilder(activity,DB.class,"databas.db")
                .allowMainThreadQueries()
                .build();

        db.reposDao().deleterepo(sto);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }


    public void delete(Repos repos){

    }
    public void restoreItem( int position) {
       notifyItemChanged(position);
    }
}
