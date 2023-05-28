package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.dimitriusdev.models.AuthModel;
import com.dimitriusdev.models.Customer;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.ProfileApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProfileViewModel extends AndroidViewModel {

    private AuthProvider authProvider;
    private final MutableLiveData<List<Project>> projectItemModels;
    private final MutableLiveData<AuthModel> authModel;
    //private final MutableLiveData<AuthModel> authModel;

    private AuthRepo authRepo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        authProvider = AuthProvider.getInstance();
        authRepo = AuthRepo.getInstance(getApplication());

        Log.i("INIT", "ProfileViewModel");
        //thisCustomer = new Customer("Dima", "test");

        projectItemModels = new MutableLiveData<>(new ArrayList<>());
        authModel = new MutableLiveData<>(new AuthModel());
    }

    public void load() {
        authModel.postValue(authRepo.getAuthModel());

        new ProfileApi().createRequest()
                .getProjects(authRepo.getAuthModel().getToken(),
                        authRepo.getAuthModel().getLogin()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                Log.i("internet", String.valueOf(response.code()));
                if(response.code() == 200){
                    Log.i("internet", response.body().toString());
                    projectItemModels.postValue(response.body());
                } else if (response.code() == 401){
                    authProvider.unauthorize();
                } else {
                    projectItemModels.postValue(new ArrayList<>());
                }
            }
            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.i("internet", t.toString());
                projectItemModels.postValue(new ArrayList<>());
            }
        });
    }

    public void addProject(Project project) {
        List<Project> list = projectItemModels.getValue();

        project.setAuthorCustomer(
                new Customer(authRepo.getAuthModel().getLogin(), authRepo.getAuthModel().getPassword()
                ));
        list.add(project);
        projectItemModels.postValue(list);
    }

    public void removeProject(int id) {
        List<Project> list = projectItemModels.getValue();
        list.remove(id);
        projectItemModels.postValue(list);
    }

    public LiveData<List<Project>> getProjectItemModelsLiveData() {
        return projectItemModels;
    }
    public LiveData<AuthModel> getAuthModelLiveData() { return authModel; }
}