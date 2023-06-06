package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.SubsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsViewModel extends AndroidViewModel {
    private AuthRepo authRepo;
    private final MutableLiveData<List<ProjectModel>> projectItemModels;

    public SubsViewModel(@NonNull Application application) {
        super(application);
        Log.i("INIT", "SubsViewModel");

        authRepo = AuthRepo.getInstance(getApplication());

        projectItemModels = new MutableLiveData<>(new ArrayList<>());
    }

    public void load() {
        new SubsApi().createRequest()
                .getCustomerSubs(authRepo.getAuthModel().getToken(),
                        authRepo.getAuthModel().getLogin()).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<List<ProjectModel>> call, Response<List<ProjectModel>> response) {
                        Log.i("internet", String.valueOf(response.code()));
                        if(response.code() == 200){
                            Log.i("internet", response.body().toString());
                            projectItemModels.postValue(response.body());
                        } else if(response.code() == 401) {
                            AuthProvider.getInstance().reauthorize();
                        } else {
                            projectItemModels.postValue(new ArrayList<ProjectModel>());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProjectModel>> call, Throwable t) {
                        Log.i("internet", t.toString());
                        projectItemModels.postValue(new ArrayList<ProjectModel>());
                    }
                });
    }


    public void removeProject(int id) {
        List<ProjectModel> list = projectItemModels.getValue();
        list.remove(id);
        projectItemModels.postValue(list);
    }

    public LiveData<List<ProjectModel>> getProjectItemModels() {
        return projectItemModels;
    }
}
