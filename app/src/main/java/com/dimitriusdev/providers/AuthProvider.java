package com.dimitriusdev.providers;

import android.content.Context;
import android.util.Log;

import com.dimitriusdev.models.AuthModel;

public class AuthProvider {
    private static AuthProvider instance;

    public static AuthProvider getInstance() {
        if (instance == null) {
            instance = new AuthProvider();
        }
        Log.i("INIT ONCE", "AuthProvider");
        return instance;
    }


    private AuthModel authModel;
    private AuthProvider() {
        this.authModel = new AuthModel("Dima", "testPassword");
    }

    public AuthModel getAuthModel() {
        return authModel;
    }
}
