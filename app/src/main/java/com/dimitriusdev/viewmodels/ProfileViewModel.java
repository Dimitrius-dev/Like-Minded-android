package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dimitriusdev.models.AuthModel;
import com.dimitriusdev.models.CustomerModel;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.ProjectModel;
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
    private final MutableLiveData<List<ProjectModel>> projectItemModels;
    private final MutableLiveData<AuthModel> authModel;
    //private final MutableLiveData<AuthModel> authModel;

    private AuthRepo authRepo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        authProvider = AuthProvider.getInstance();
        authRepo = AuthRepo.getInstance(getApplication());

        Log.i("INIT", "ProfileViewModel");
        //thisCustomer = new Customer("Dima", "test");

        projectItemModels = new MutableLiveData<>();
        authModel = new MutableLiveData<>(new AuthModel());
    }

    public void load() {
        authModel.postValue(authRepo.getAuthModel());

        new ProfileApi().createRequest()
                .getProjects(authRepo.getAuthModel().getToken(),
                        authRepo.getAuthModel().getLogin()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ProjectModel>> call, Response<List<ProjectModel>> response) {
                Log.i("internet", String.valueOf(response.code()));
                if(response.code() == 200){
                    Log.i("internet", response.body().toString());
                    projectItemModels.postValue(response.body());
                } else if (response.code() == 401){
                    authProvider.reauthorize();
                } else {
                    projectItemModels.postValue(new ArrayList<>());
                }
            }
            @Override
            public void onFailure(Call<List<ProjectModel>> call, Throwable t) {
                Log.i("internet", t.toString());
                projectItemModels.postValue(new ArrayList<>());
            }
        });
    }

    public void addProject(ProjectModel projectModel) {
        List<ProjectModel> list = projectItemModels.getValue();

        projectModel.setAuthorCustomer(
                new CustomerModel(authRepo.getAuthModel().getLogin(), authRepo.getAuthModel().getPassword()
                ));
        list.add(projectModel);
        projectItemModels.postValue(list);
    }

    public void removeProject(ProjectModel projectModel) {
//        List<ProjectModel> projectModelList = projectItemModels.getValue();
//        });

    }

    public LiveData<List<ProjectModel>> getProjectItemModelsLiveData() {
        return projectItemModels;
    }
    public LiveData<AuthModel> getAuthModelLiveData() { return authModel; }
}