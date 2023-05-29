package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.ProjectApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ProjectViewModel extends AndroidViewModel {

    private AuthProvider authProvider;
    private AuthRepo authRepo;
    private final MutableLiveData<ProjectModel> projectModelLiveData;
    //private final MutableLiveData<AuthModel> authModel;


    public ProjectViewModel(@NonNull Application application) {
        super(application);
        authProvider = AuthProvider.getInstance();
        authRepo = AuthRepo.getInstance(getApplication());

        Log.i("INIT", "ProfileViewModel");
        //thisCustomer = new Customer("Dima", "test");

        projectModelLiveData = new MutableLiveData<>();
    }


    public void createProject(ProjectModel projectModel) {
        new ProjectApi().createRequest().createProject(authRepo.getAuthModel().getToken(),
                projectModel,
                authRepo.getAuthModel().getLogin()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                Log.i("CHECK", "start0");
                if (response.code() == 200) {
                    Log.i("CHECK", "start1");
                    projectModelLiveData.postValue(projectModel);
                } else if (response.code() == 401) {
                    authProvider.unauthorize();
                } else {
                    Toast.makeText(getApplication(), "change project name", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {
                Toast.makeText(getApplication(), "connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<ProjectModel> getProjectModel() {
        return projectModelLiveData;
    }
}