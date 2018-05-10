package com.cheesecake.articles;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Pedro Henrique on 11/01/2015.
 */
public interface ApiService {

    /*
        REST API to interface convertion through Retrofit type-safe REST client for Android.
        More info in: http://square.github.io/retrofit/ or https://github.com/square/retrofit
    */
    @GET("/challenge/")
    public void getArticles(Callback<ArrayList<Article>> callback);
}
