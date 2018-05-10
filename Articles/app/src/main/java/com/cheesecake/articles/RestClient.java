package com.cheesecake.articles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


/**
 * Created by Pedro Henrique on 11/01/2015.
 */
/**
 * Entity responsible of taking care of the network requests.
 * It creates a boudary between the application and the cloud.
 */
public class RestClient {

    // URL where to get the articles JSON array from.
    private static final String ARTICLES_URL = "http://www.ckl.io";

    // ApiService interface with get method.
    private static ApiService apiService;


    static {
        setupRestClient();
    }

    private RestClient() { }


    /**
     * Sets up the client with some required information.
     */
    private static void setupRestClient() {
        Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

        // Create a very simple REST adapter which points the CKL API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ARTICLES_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        // Create an instance of RESTArticles interface.
        apiService = restAdapter.create(ApiService.class);
    }


    /**
     * Returns the ApiService interface.
     */
    public static ApiService getApiService() {
        return apiService;
    }
}
