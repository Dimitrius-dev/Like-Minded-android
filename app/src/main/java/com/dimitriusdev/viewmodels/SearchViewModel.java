package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dimitriusdev.models.Project;
import com.dimitriusdev.repository.api.ProfileApi;
import com.dimitriusdev.repository.api.SearchApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class SearchViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Project>> projectItemModels;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        Log.i("INIT", "ProfileViewModel");

        projectItemModels = new MutableLiveData<>(new ArrayList<>());
    }

    public void load() {
        new SearchApi().createRequest().getProjects().enqueue(new Callback<>() {
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