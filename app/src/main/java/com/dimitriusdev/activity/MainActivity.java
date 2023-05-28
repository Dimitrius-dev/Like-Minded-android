package com.dimitriusdev.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;


import com.dimitriusdev.fragments.AuthFragment;
import com.dimitriusdev.fragments.navigation.NavigationFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.viewmodels.AuthViewModel;

public final class MainActivity extends AppCompatActivity {

    private AuthRepo authRepo;
    private AuthViewModel authViewModel;
    private AuthProvider authProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("INIT", "MainActivity");

        authProvider = AuthProvider.getInstance();

        this.authRepo = AuthRepo.getInstance(getBaseContext());
        this.authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authProvider.getAccess().observe(this, access -> {
            if(access){
                Log.i("INIT", "activity auth true");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentMainContainer, NavigationFragment.class, null)
                        .commit();
            } else {
                //Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                Log.i("INIT", "activity auth false");
                authRepo.clearAuthModel();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentMainContainer, AuthFragment.class, null)
                        .commit();
            }
        });

        // empty first start
        if(authRepo.isEmpty()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentMainContainer, AuthFragment.class, null)
                    .commit();

        } else {
            authViewModel.auth(
                    authRepo.getAuthModel().getLogin(),
                    authRepo.getAuthModel().getPassword()
            );
        }

    }
}