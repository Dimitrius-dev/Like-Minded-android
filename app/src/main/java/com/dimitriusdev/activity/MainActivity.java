package com.dimitriusdev.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import com.dimitriusdev.fragments.AuthFragment;
import com.dimitriusdev.fragments.navigation.NavigationFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.providers.AuthProvider;

public final class MainActivity extends AppCompatActivity {

    private AuthProvider authProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.authProvider = AuthProvider.getInstance();

        if(authProvider.getAuthModel().getLogin().isEmpty()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainerMenu, AuthFragment.class, null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainerMenu, NavigationFragment.class, null)
                    .commit();
        }


    }
}