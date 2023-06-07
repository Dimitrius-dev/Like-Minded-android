package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.ProjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IProfileApi {

    @GET("/customer/{login}/projects")
    Call<List<ProjectModel>> getProjects(@Header("Authorization") String token,
                                         @Path("login") String login);
    @DELETE("/customer/{login}/project/{name}")
    Call<MsgModel> removeProject(@Header("Authorization") String token,
                                 @Path("login") String login,
                                 @Path("name") String name);

}
