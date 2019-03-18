package com.bkstudios.marvelapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyProfileHolder extends RecyclerView.ViewHolder {
    View view=null;
    public MyProfileHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }
    public void setData(Repos repos){
        TextView reps_name=view.findViewById(R.id.rep_name);
        TextView reps_lang=view.findViewById(R.id.rep_lang);
        reps_name.setText(repos.getName());
        reps_lang.setText(repos.getLanguage());
    }
}
