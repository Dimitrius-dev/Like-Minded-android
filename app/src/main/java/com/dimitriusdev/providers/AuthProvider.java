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


    private AuthProvider() {
        access = new MutableLiveData<>();
    };

    private final MutableLiveData<Boolean> access;

    public LiveData<Boolean> getAccess(){ return access; }

    public void authorize(){
        access.postValue(true);
    }

    public void unauthorize(){
        access.postValue(false);
    }


}
