package com.dimitriusdev.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.fragments.navigation.NavigationFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.List;

public final class AuthFragment extends ConfiguredFragment {
//    private ProfileViewModel profileViewModel;
//    private RecyclerView projectRecyclerView;
//    private ProfileProjectListAdapter profileProjectListAdapter;
//    private List<Project> profileProjects;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonCreateAccount;
    private Button buttonLogIn;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("INIT", "ProfileFragment");

        buttonLogIn = view.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(v -> {
//            getActivity().getSupportFragmentManager().beginTransaction()
////            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentMainContainer, NavigationFragment.class, null)
//                    .commit();
            switchToRoot();
            //switchToFragment(R.id.fragmentMainContainer, NavigationFragment.class, false);
        });

        buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        buttonCreateAccount.setOnClickListener(v -> {

            getActivity().getSupportFragmentManager().beginTransaction()
                    //getChildFragmentManager().beginTransaction()
                    .add(R.id.fragmentMainContainer, RegisterFragment.class, null)
                    .addToBackStack(null)
                    .commit();

            //switchToFragment(R.id.fragmentMainContainer, RegisterFragment.class, true);
        });

//        profileViewModel.load();
    }
}