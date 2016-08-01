package com.spb.kbv.etest.infrastructure;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtestApplication extends Application {

    private WebService mApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mApi = createWebService();
    }

    public WebService getApi() {
        return mApi;
    }

    private static WebService createWebService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://careers.ekassir.com/test/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(WebService.class);
    }
}
