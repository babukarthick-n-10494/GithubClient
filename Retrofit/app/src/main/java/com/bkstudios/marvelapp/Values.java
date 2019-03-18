package com.bkstudios.marvelapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Values {

    @GET("users/karthickcnm/repos")
    public Call<ResponseBody> getmydata();

    @GET("search/users")
    public Call<ResponseBody> getUsersList(@Query("q") String searchKeyword);




    @GET("users/{username}/repos")
    public Call<ResponseBody> getUserData(@Path("username") String username);


}
