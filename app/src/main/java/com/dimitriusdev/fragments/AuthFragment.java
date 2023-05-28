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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.fragments.navigation.NavigationFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.AuthViewModel;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.List;

public final class AuthFragment extends ConfiguredFragment {

    private AuthViewModel authViewModel;
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
        Log.i("INIT", "AuthFragment");

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        editTextLogin = view.findViewById(R.id.editTextLoginAuth);
        editTextPassword = view.findViewById(R.id.editTextPasswordAuth);


        buttonLogIn = view.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(v -> {
            buttonLogIn.setEnabled(false);
            buttonCreateAccount.setEnabled(false);

            authViewModel.auth(
                    editTextLogin.getText().toString(),
                    editTextPassword.getText().toString()
            );
//            getActivity().getSupportFragmentManager().beginTransaction()
////            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentMainContainer, NavigationFragment.class, null)
//                    .commit();
            //switchToFragment(R.id.fragmentMainContainer, NavigationFragment.class, false);
        });


        buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        buttonCreateAccount.setOnClickListener(v -> {
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    //getChildFragmentManager().beginTransaction()
//                    .add(R.id.fragmentMainContainer, RegisterFragment.class, null)
//                    .addToBackStack(null)
//                    .commit();

            switchToFragment(R.id.fragmentMainContainer, RegisterFragment.class, true);
        });

//        authViewModel.getAccess().observe(requireActivity(), access -> {
//            buttonLogIn.setEnabled(true);
//            buttonCreateAccount.setEnabled(true);
//            if(access) {
//                Log.i("NEXT", "register");
//                switchToFragment(R.id.fragmentMainContainer, RegisterFragment.class, true);
//            } else {
//                Log.i("NEXT", "root");
//                //switchToRoot();
//            }
//        });

        authViewModel.getAuthModel().observe(requireActivity(), authModel -> {
            editTextLogin.setText(authModel.getLogin());
            editTextPassword.setText(authModel.getPassword());
        });

        authViewModel.load();
    }
}