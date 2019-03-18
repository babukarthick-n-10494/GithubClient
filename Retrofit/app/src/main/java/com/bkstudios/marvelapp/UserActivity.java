package com.bkstudios.marvelapp;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bkstudios.marvelapp.databinding.UserprofileBinding;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    UserprofileBinding binding = null;
    ArrayList<Repos> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.userprofile);

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("userclick");
         new UserThread(binding,UserActivity.this,user,data).execute();


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            binding.img.setTransitionName("test");
//        }

        RecyclerView recyclerView= (RecyclerView)findViewById(R.id.lastlist);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);

        UserAdapter adapter=new UserAdapter(data,this,binding);

        recyclerView.setAdapter(adapter);

    }

}
