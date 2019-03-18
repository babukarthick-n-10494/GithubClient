package com.bkstudios.marvelapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@Database(entities ={Repos.class},version = 1,exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract ReposDao reposDao();
}
