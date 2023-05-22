package com.dimitriusdev.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dimitriusdev.models.AuthModel;

public class AuthProvider {
    private static AuthProvider instance;

    public static AuthProvider getInstance(Context context) {
        if (instance == null) {
            instance = new AuthProvider(context);
        }
        Log.i("INIT ONCE", "AuthProvider");
        return instance;
    }

    //private AuthModel authModel;

    //Dima
    private Context context;
    private AuthProvider(Context context) {
        this.context = context;
        //this.authModel = new AuthModel("", "");
    }

    private static String PREFERENCES_ID = "auth_config";
    private static String PREFERENCES_LOGIN = "login";
    private static String PREFERENCES_PASSWORD = "password";
    private static String PREFERENCES_TOKEN = "token";
    public void setAuthModel(AuthModel authModel){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(PREFERENCES_LOGIN, authModel.getLogin());
        editor.putString(PREFERENCES_PASSWORD, authModel.getPassword());
        editor.putString(PREFERENCES_TOKEN, authModel.getToken());
        editor.apply();
    }
    public void clearAuthModel(){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear();
        //editor.apply();
    }

    public AuthModel getAuthModel(){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_ID, Context.MODE_PRIVATE);


        return new AuthModel(
                sharedPref.getString(PREFERENCES_LOGIN, ""),
                sharedPref.getString(PREFERENCES_PASSWORD, ""),
                sharedPref.getString(PREFERENCES_TOKEN, "")
        );
    }

//    public AuthModel getAuthModel() {
//        return authModel;
//    }
}
