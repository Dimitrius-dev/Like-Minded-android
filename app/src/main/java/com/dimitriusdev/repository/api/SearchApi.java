package com.dimitriusdev.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchApi extends BaseApi{

    private ISearchApi iSearchApi;

    public SearchApi(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getServiceUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iSearchApi = retrofit.create(ISearchApi.class);
    }

    public ISearchApi createRequest(){
        return iSearchApi;
    }

}
