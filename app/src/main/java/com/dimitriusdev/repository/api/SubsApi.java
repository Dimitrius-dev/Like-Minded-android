package com.dimitriusdev.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubsApi extends BaseApi{


    private ISubsApi iSubsApi;

    public SubsApi(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getServiceUri())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iSubsApi = retrofit.create(ISubsApi.class);
    }

    public ISubsApi createRequest(){
        return iSubsApi;
    }

}
