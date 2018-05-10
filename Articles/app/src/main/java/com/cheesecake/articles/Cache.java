package com.cheesecake.articles;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Pedro Henrique on 11/01/2015.
 */
public class Cache {
    /*
        Cache implements the singleton patter, so that other entities
        in the application would be able to access its data.
    */
    private static Cache sharedInstance = null;

    // List of articles in the cache.
    private static ArrayList<Article> articlesCache;


    /**
     * Constructor.
     */
    private Cache() {
        articlesCache = new ArrayList<>();
        getArticlesFromUrl();
    }


    /**
     * Creates a new instance of the singleton
     */
    public static void initInstance() {
        if(sharedInstance == null) {
            sharedInstance = new Cache();
        }
    }


    /**
     * Returns the instance of this cache singleton.
     * @return Instance of this class.
     */
    public static Cache getSharedInstance() {
        initInstance();
        return sharedInstance;
    }


    /**
     * Make the request to the api service to retrieve the articles objects.
     */
    private void getArticlesFromUrl() {
        RestClient.getApiService().getArticles(new Callback<ArrayList<Article>>() {
            @Override
            public void success(ArrayList<Article> articles, Response response) {
                articlesCache = articles;
            }

            @Override
            public void failure(RetrofitError error) { }
        });
    }


    /**
     * Returns the list containing the articles.
     * @return Articles list stored in this cache entity.
     */
    public ArrayList<Article> getArticlesCache() {
        return articlesCache;
    }
}
