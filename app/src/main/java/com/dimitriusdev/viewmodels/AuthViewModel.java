package com.dimitriusdev.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dimitriusdev.models.AuthModel;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.AuthApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class AuthViewModel extends AndroidViewModel {
    private final MutableLiveData<AuthModel> authModel;
    private AuthRepo authRepo;
    private AuthProvider authProvider;

    public AuthViewModel(@NonNull Application application) {
        super(application);

        needToReconnect = true;

        authProvider = AuthProvider.getInstance();

        authRepo = AuthRepo.getInstance(getApplication());

        authModel = new MutableLiveData<>(new AuthModel());
    }

    private boolean needToReconnect;
    public void load(){
        authModel.postValue(authRepo.getAuthModel());
    }

    public void auth(String login, String password) {
        authRepo.setAuthModel(new AuthModel(login, password, ""));
        Log.i("CHECK", authRepo.getAuthModel().getLogin());
        auth();
    }

    public void auth() {
        new AuthApi().createRequest()
                .logIn(authRepo.getAuthModel())
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
                        if ((response.code() == 200) &&
                                (response.body().getPassword().equals(authRepo.getAuthModel().getPassword()))
                        ) {
                            Log.i("INIT", "auth true");
                            authRepo.setAuthModel(response.body());
                            authProvider.authorize();
                        } else {
                            Log.i("INIT", "auth false");
                            Toast.makeText(getApplication(), "auth failed", Toast.LENGTH_SHORT).show();
                            authProvider.ignore();

//                            if(needToReconnect){
//                                needToReconnect = false;
//                                authProvider.reauthorize();
//                            } else {
//                                authProvider.unauthorize();
//                                Toast.makeText(getApplication(), "auth failed", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthModel> call, Throwable t) {
                        Toast.makeText(getApplication(), "network fail", Toast.LENGTH_SHORT).show();
                        Log.i("INIT", "auth false (fail)");
                        authProvider.ignore();
                    }
                });
    }

    public void register(AuthModel newAuthModel) {
        new AuthApi().createRequest()
                .register(newAuthModel)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
                        Log.i("REGISTER", String.valueOf(response.code()));
                        if (response.code() == 200) {
                            Log.i("INIT", "auth true");
                            authRepo.setAuthModel(response.body());
                            authProvider.authorize();
                        } else {
                            Log.i("INIT", "registration failed");
                            //authProvider.unauthorize();
                            Toast.makeText(getApplication(), "enter unique login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthModel> call, Throwable t) {
                        Toast.makeText(getApplication(), "network fail", Toast.LENGTH_SHORT).show();
                        Log.i("INIT", "auth false (fail)");
                        authProvider.unauthorize();
                    }
                });
    }

    public LiveData<AuthModel> getAuthModel(){ return authModel; }
}