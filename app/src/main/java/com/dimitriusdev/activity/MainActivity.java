package com.dimitriusdev.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import com.dimitriusdev.fragments.NavigationFragment;
import com.dimitriusdev.likeminded.R;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerMenu, NavigationFragment.class, null)
                .commit();
    }
}