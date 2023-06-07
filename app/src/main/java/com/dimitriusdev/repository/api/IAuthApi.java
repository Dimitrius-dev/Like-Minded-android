package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.AuthModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthApi {

    @POST("/auth/login")
    Call<AuthModel> logIn(@Body AuthModel authModel);

    @POST("/auth/register")
    Call<AuthModel> register(@Body AuthModel authModel);

//    @GET("projects")
//    Call<List<Project>> getProjects();

}
