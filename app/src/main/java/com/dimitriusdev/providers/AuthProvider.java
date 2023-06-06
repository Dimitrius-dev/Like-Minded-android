package com.dimitriusdev.providers;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AuthProvider  {
    private static AuthProvider INSTANCE = null;
    // other instance variables can be here


    public static synchronized AuthProvider getInstance() {
        if (INSTANCE == null) {
            Log.i("INIT ONCE", "new AuthProvider");
            INSTANCE = new AuthProvider();
        }
        return INSTANCE;
    }

    private final MutableLiveData<AuthStatus> access;
    private AuthProvider() {
        access = new MutableLiveData<>();
    };

    public LiveData<AuthStatus> getAccess(){ return access; }

    public void authorize(){
        access.postValue(AuthStatus.AUTH);
    }
    public void reauthorize(){
        access.postValue(AuthStatus.RE_AUTH);
    }

    public void unauthorize(){
        access.postValue(AuthStatus.UN_AUTH);
    }


}
