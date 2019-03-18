package com.bkstudios.marvelapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bkstudios.marvelapp.databinding.PlainactivityBinding;

public class PlainActivity extends AppCompatActivity {
    PlainactivityBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(PlainActivity.this,R.layout.plainactivity);

        new PlainThread(binding,PlainActivity.this).execute();
    }
}
