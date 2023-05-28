package com.dimitriusdev.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApi extends BaseApi{

    private IAuthApi iauthApi;

    public AuthApi(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getServiceUri())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        iauthApi = retrofit.create(IAuthApi.class);
    }

    public IAuthApi createRequest(){
        return iauthApi;
    }

}
