package com.dimitriusdev.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dimitriusdev.models.Project;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.api.ProfileApi;
import com.dimitriusdev.repository.api.SubsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsViewModel extends ViewModel {
    private AuthProvider authProvider;
    private final MutableLiveData<List<Project>> projectItemModels;


    public SubsViewModel() {
        Log.i("INIT", "SubsViewModel");

        authProvider = AuthProvider.getInstance();

        projectItemModels = new MutableLiveData<>(new ArrayList<>());
    }

    public void load() {
        new SubsApi().createRequest()
                .getCustomerSubs(authProvider.getAuthModel().getLogin()).enqueue(new Callback<>() {
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


    public void removeProject(int id) {
        List<Project> list = projectItemModels.getValue();
        list.remove(id);
        projectItemModels.postValue(list);
    }

    public LiveData<List<Project>> getProjectItemModels() {
        return projectItemModels;
    }
}
