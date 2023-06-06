package com.dimitriusdev.repository.api;

import com.dimitriusdev.repository.AuthRepo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BaseApi {

    //    IP
    //    HOME:     http://192.168.100.7:9090/
    //    SERVER:   http://45.143.137.96:9090/
    private final String BASE_URL = "http://192.168.100.5:9090";
    protected OkHttpClient client;
    public BaseApi(){
        client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    String getServiceUri(){
        return BASE_URL;
    }

}
