package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.Customer;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ISearchApi {
    @GET("/projects")
    Call<List<Project>> getProjects(@Header("Authorization") String token);

    @POST("/customer/{login}/sub/{name}")
    Call<MsgModel> subscribeOnProject(@Header("Authorization") String token,
                                      @Path("login") String login, @Path("name") String name);

}
