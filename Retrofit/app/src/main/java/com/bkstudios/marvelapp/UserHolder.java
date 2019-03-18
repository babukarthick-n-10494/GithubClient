package com.bkstudios.marvelapp;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bkstudios.marvelapp.databinding.UserprofileBinding;

public class UserHolder extends RecyclerView.ViewHolder {

    View view=null;
    Activity activity;
    UserprofileBinding binding;

    TextView userRepName ;
    TextView userRepLan;
    View black;
    View white;


    public UserHolder(@NonNull View itemView, Activity activity, UserprofileBinding binding) {
        super(itemView);
        view=itemView;
        this.activity=activity;
        this.binding=binding;

    }

    public void setData(final Repos repos){
         userRepName = view.findViewById(R.id.rep_name);
         userRepLan = view.findViewById(R.id.rep_lang);
          black = view.findViewById(R.id.blackStar);
          white= view.findViewById(R.id.whiteStar);


        if (repos.isFav) {
            white.setVisibility(View.GONE);
            black.setVisibility(View.VISIBLE);
        } else {
            white.setVisibility(View.VISIBLE);
            black.setVisibility(View.GONE);
        }

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                white.setVisibility(View.GONE);
                black.setVisibility(View.VISIBLE);
                repos.setFav(true);
                insert();
            }
        });

//        black.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                black.setVisibility(View.GONE);
//                white.setVisibility(View.VISIBLE);
//                repos.setFav(false);
////                delete(repos);
//            }
//        });
        userRepName.setText(repos.getName());
        userRepLan.setText(repos.getLanguage());
    }

    public void insert(){
        String name=userRepName.getText().toString();
        String lang=userRepLan.getText().toString();
        String userName=binding.name.getText().toString();


        Repos repos = new Repos();
        repos.language = lang;
        repos.name = name;
        repos.userName = userName;


        DB db = Room.databaseBuilder(activity,DB.class,"databas.db")
                .allowMainThreadQueries()
                .build();

        db.reposDao().insertRepos(repos);


//
//
//        ContentValues values=new ContentValues();
//        values.put(Data.title,name);
//        values.put(Data.subTitle,lang);
//        values.put(Data.username,userName);
//        Database db=new Database(activity);
//        db.getWritableDatabase().insert(Data.TABLE_NAME,null,values);

    }



}
