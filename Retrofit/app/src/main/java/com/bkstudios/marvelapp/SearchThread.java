package com.bkstudios.marvelapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.bkstudios.marvelapp.databinding.SearchactivityBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchThread extends AsyncTask<String,String,String> {
    SearchAdapter adapter;
    SearchactivityBinding binding=null;
    String value;

        public SearchThread(SearchactivityBinding binding,String value,SearchAdapter adapter){
        this.value=value;
        this.binding=binding;
        this.adapter=adapter;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        binding.searchxml.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(String... strings) {
        String responses ="";
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("https://api.github.com/");

        Retrofit retrofit = builder.build();

        Values values = retrofit.create(Values.class);

        try {
            Response<ResponseBody> respBody =   values.getUsersList(value).execute();
            responses = respBody.body().string();
            return  responses;

        }

        catch (Exception e) {
            e.printStackTrace();
        }


        return  null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        binding.progressBar.setVisibility(View.GONE);
        binding.searchList.setVisibility(View.VISIBLE);
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            ArrayList<Search> data=new ArrayList<>();
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonObject1=itemsArray.getJSONObject(i);
                Search search = new Search();
                 search.setAvatar(jsonObject1.getString("avatar_url"));
                 search.setLogin(jsonObject1.getString("login"));
                 Log.d(jsonObject1.getString("login"),"hi");
                data.add(search);

            }
            adapter.addData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
