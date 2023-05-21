package com.dimitriusdev.repository.api;

import com.dimitriusdev.models.Customer;
import com.dimitriusdev.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ISubsApi {

    @GET("/customer/{login}/subs")
    Call<List<Project>> getCustomerSubs(@Path("login") String login);

    @POST("/customer/{login}/unsub")
    Call<List<Project>> getCustomerSubs(@Path("login") String login);

}
