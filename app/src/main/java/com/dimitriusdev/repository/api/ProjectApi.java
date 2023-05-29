package com.dimitriusdev.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectApi extends BaseApi{

    private IProjectApi iProjectApi;

    public ProjectApi(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getServiceUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        iProjectApi = retrofit.create(IProjectApi.class);
    }

    public IProjectApi createRequest(){
        return iProjectApi;
    }

}
