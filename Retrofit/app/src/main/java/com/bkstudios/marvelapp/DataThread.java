package com.bkstudios.marvelapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataThread extends AsyncTask<Void,String,ArrayList<Repos>> {

    Context context = null;
    ArrayList<Repos> data = new ArrayList<>();
    DataAdapter adapter = null;

    public DataThread(Context context, ArrayList<Repos> data, DataAdapter adapter) {
        this.context = context;
        this.data = data;
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<Repos> doInBackground(Void... voids) {
        DB db = Room.databaseBuilder(context, DB.class, "databas.db")
                .build();

        List<Repos> list = db.reposDao().getAllRepos();
        data.addAll(list);

        return data;
    }
}

