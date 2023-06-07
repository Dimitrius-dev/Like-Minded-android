package com.dimitriusdev.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileApi extends BaseApi{

    private IProfileApi iProfileApi;

    public ProfileApi(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getServiceUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iProfileApi = retrofit.create(IProfileApi.class);
    }

    public IProfileApi createRequest(){
        return iProfileApi;
    }

}
