package com.bkstudios.marvelapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReposDao {

    @Insert
    public void insertRepos(Repos repos);

    @Query("SELECT * FROM Repos")
    public List<Repos> getAllRepos();

    @Delete
    public void deleterepo(Repos repos);

}
