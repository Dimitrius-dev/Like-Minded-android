package com.dimitriusdev.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dimitriusdev.models.Customer;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.repository.api.ProfileApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProfileViewModel extends ViewModel {

    private Customer thisCustomer;
    private final MutableLiveData<List<Project>> projectItemModels;

    public ProfileViewModel() {

        Log.i("INIT", "ProfileViewModel");
        thisCustomer = new Customer("Dima", "test");

        projectItemModels = new MutableLiveData<>(new ArrayList<>());
    }

    public void load() {

        new ProfileApi().createRequest()
                .getProjects(thisCustomer.getLogin()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                Log.i("internet", String.valueOf(response.code()));
                if(response.code() == 200){
                    Log.i("internet", response.body().toString());
                    projectItemModels.postValue(response.body());
                } else {
                    projectItemModels.postValue(new ArrayList<Project>());
                }
            }
            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.i("internet", t.toString());
                projectItemModels.postValue(new ArrayList<Project>());
            }
        });
    }

    public void addProject(Project project) {
        List<Project> list = projectItemModels.getValue();

        project.setAuthorCustomer(thisCustomer);
        list.add(project);
        projectItemModels.postValue(list);
    }

    public void removeProject(int id) {
        List<Project> list = projectItemModels.getValue();
        list.remove(id);
        projectItemModels.postValue(list);
    }

    public LiveData<List<Project>> getProjectItemModels() {
        return projectItemModels;
    }
}