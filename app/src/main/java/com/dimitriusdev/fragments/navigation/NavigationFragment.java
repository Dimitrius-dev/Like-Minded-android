package com.dimitriusdev.fragments.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.likeminded.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public final class NavigationFragment extends ConfiguredFragment {

    private ProfileFragment profileFragment;
    private SearchListFragment projectsFragment;
    private SubsFragment agreedListFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getChildFragmentManager().beginTransaction()
//                .add(R.id.fragmentContainerMenu, profileFragment, null)
//                .commit();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationMenu);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_projects:
                        switchToFragment(R.id.fragmentMenuContainer, SearchListFragment.class, false);
                        return true;
                    case R.id.item_profile:
                        switchToFragment(R.id.fragmentMenuContainer, ProfileFragment.class, false);
                        return true;
                    case R.id.item_agreed_list:
                        switchToFragment(R.id.fragmentMenuContainer, SubsFragment.class, false);
                        return true;
                }

                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.item_profile);


//        view.findViewById(R.id.button_counter).setOnClickListener(v -> {
//            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentContainerMenu, profileFragment, null)
//                    .commit();
//        });
//        view.findViewById(R.id.button_list).setOnClickListener(v -> {
//            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentContainerMenu, listFragment, null)
//                    .commit();
//        });
//        view.findViewById(R.id.button_news).setOnClickListener(v -> {
//            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentContainerMenu, newsFragment, null)
//                    .commit();
//        });
    }
}