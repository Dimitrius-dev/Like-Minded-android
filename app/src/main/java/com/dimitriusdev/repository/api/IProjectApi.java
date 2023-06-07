package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.ProjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IProjectApi {

    @POST("/customer/{login}/project")
    Call<MsgModel> createProject(@Header("Authorization") String token,
                                  @Body ProjectModel projectModel,
                                  @Path("login") String login);

}
