package com.cheesecake.articles;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by Pedro Henrique on 11/01/2015.
 */
public interface RESTArticles {

    /*
        REST API to interface convertion through Retrofit type-safe REST client for Android.
        More info in: http://square.github.io/retrofit/ or https://github.com/square/retrofit
    */

    @GET("/")
    List<Article> articles();
}
