package com.bkstudios.marvelapp;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DataHolder extends RecyclerView.ViewHolder {
    View view=null;
    Activity activity;
    ImageView black;
    RelativeLayout viewforeground;
    public DataHolder(@NonNull View itemView,Activity activity,DataAdapter adapter) {
        super(itemView);
        this.activity=activity;
        view = itemView;
       viewforeground  = view.findViewById(R.id.view_foreground);
    }

    public void setData(final Repos repos){
        TextView  title= view.findViewById(R.id.name);
        TextView subTitle=view.findViewById(R.id.description);
        RelativeLayout viewbackground = view.findViewById(R.id.view_background);

        title.setText(repos.getName());
        subTitle.setText(repos.getLanguage());

    }


}
