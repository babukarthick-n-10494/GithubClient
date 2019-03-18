package com.bkstudios.marvelapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class Stertho extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
