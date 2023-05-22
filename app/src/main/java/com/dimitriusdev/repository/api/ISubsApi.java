package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.Customer;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ISubsApi {

    @GET("/customer/{login}/subs")
    Call<List<Project>> getCustomerSubs(@Path("login") String login);

    @DELETE("/customer/{login}/sub/{name}")
    Call<MsgModel> unsubscribeOnSub(@Path("login") String login, @Path("name") String name);

}
