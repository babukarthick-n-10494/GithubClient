package com.bkstudios.marvelapp;

import android.content.Intent;
import android.os.AsyncTask;

import com.bkstudios.marvelapp.databinding.PlainactivityBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlainThread extends AsyncTask <String,String,String>{
    ArrayList<Repos> list=new ArrayList<>();
    PlainactivityBinding binding = null;
    PlainActivity activity;


    public PlainThread(PlainactivityBinding binding, PlainActivity activity) {
        this.binding  = binding;
        this.activity  = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
       String responses ="";
       Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("https://api.github.com/");

        Retrofit retrofit = builder.build();

        Values values = retrofit.create(Values.class);

        try {
           Response<ResponseBody> respBody =   values.getmydata().execute();
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
        String ownerImage = null;
        String ownerName = null;

        try {

            JSONArray responseArray = new JSONArray(s);
            for (int i = 0;i<responseArray.length();i++){

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



        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent=new Intent(activity,MyProfile.class);
        intent.putExtra("ownerName",ownerName);
        intent.putExtra("ownerImage",ownerImage);
        intent.putExtra("Array", list);
        activity.startActivity(intent);
        activity.finish();

    }
}
