package com.bkstudios.marvelapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bkstudios.marvelapp.databinding.SearchactivityBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchactivityBinding SearchactivityBinding = null;
    String value=null;
    ArrayList<Search> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        SearchactivityBinding = DataBindingUtil.setContentView(SearchActivity.this,R.layout.searchactivity);

       final SearchAdapter adapter=new SearchAdapter(data,SearchActivity.this);

        SearchactivityBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        SearchactivityBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearData();
                value=SearchactivityBinding.searchbar.getText().toString();
                new SearchThread(SearchactivityBinding,value,adapter).execute();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(SearchactivityBinding.searchbar.getWindowToken(), 0);

            }
        });

        SearchactivityBinding.searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    adapter.clearData();
                    value=SearchactivityBinding.searchbar.getText().toString();
                    new SearchThread(SearchactivityBinding,value,adapter).execute();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(SearchactivityBinding.searchbar.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


        RecyclerView recyclerView= (RecyclerView)findViewById(R.id.search_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);


        recyclerView.setAdapter(adapter);

    }
}
