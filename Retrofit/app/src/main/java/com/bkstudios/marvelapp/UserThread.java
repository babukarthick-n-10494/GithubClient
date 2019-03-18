package com.bkstudios.marvelapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.bkstudios.marvelapp.databinding.UserprofileBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

public class UserThread extends AsyncTask<String,String,String> {

    ArrayList<Repos> list=new ArrayList<>();
    UserprofileBinding binding = null;
    UserActivity activity;
    String username = null;


    public UserThread(UserprofileBinding binding, UserActivity activity, String username, ArrayList<Repos> list) {
        this.binding = binding;
        this.username = username;
        this.activity = activity;
        this.list=list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.header.setVisibility(View.GONE);
    }

    @Override
    protected String doInBackground(String... strings) {
        String responses ="";
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("https://api.github.com/");

        Retrofit retrofit = builder.build();

        Values values = retrofit.create(Values.class);
        try {
            Response<ResponseBody> respBody =   values.getUserData(username).execute();
            responses = respBody.body().string();
            return  responses;

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        binding.progressBar.setVisibility(View.GONE);
        binding.header.setVisibility(View.VISIBLE);
        String ownerImage = null;
        String ownerName = null;

        try {

            JSONArray responseArray = new JSONArray(s);
            for (int i=0;i<responseArray.length();i++){

                JSONObject jsonObject= responseArray.getJSONObject(i);


                if (i == 0){
                    JSONObject ownerJson = jsonObject.getJSONObject("owner");
                    ownerImage=ownerJson.getString("avatar_url");
                    ownerName=ownerJson.getString("login");
                }

                Repos repos =new Repos();
                if(jsonObject.getString("name").equals("null")){
                    repos.setName("------");
                }
                else{
                    repos.setName(jsonObject.getString("name"));
                }

                if(jsonObject.getString("language").equals("null")){
                    repos.setLanguage("----");
                }
                else {
                    repos.setLanguage(jsonObject.getString("language"));
                }
                list.add(repos);

            }

            binding.name.setText(ownerName);

            Glide.with(activity)
                    .load(ownerImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.img);





        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
