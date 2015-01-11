package com.cheesecake.articles;

import android.app.Application;

/**
 * Created by Pedro Henrique on 11/01/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSingletons();
    }


    public void initSingletons() {
        // Initialize the instance of the cache in the application.
        Cache.initInstance();
    }
}
