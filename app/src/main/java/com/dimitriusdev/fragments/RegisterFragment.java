package com.dimitriusdev.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.fragments.navigation.NavigationFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.AuthModel;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.viewmodels.AuthViewModel;

public final class RegisterFragment extends ConfiguredFragment {
//    private ProfileViewModel profileViewModel;
//    private RecyclerView projectRecyclerView;
//    private ProfileProjectListAdapter profileProjectListAdapter;
//    private List<Project> profileProjects;
    private AuthRepo authRepo;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonRegisterAndLogIn;

    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        authRepo = AuthRepo.getInstance(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("INIT", "ProfileFragment");

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        editTextLogin = view.findViewById(R.id.editTextLoginRegister);
        editTextPassword = view.findViewById(R.id.editTextPasswordRegister);


//        projectRecyclerView = view.findViewById(R.id.recycleViewProfileProjects);
//        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
//
//        profileProjects = new LinkedList<>();
//        profileProjectListAdapter = new ProfileProjectListAdapter(
//                getContext(),
//                new ArrayList<>()//profileViewModel.getProjectItemModels().getValue()
//        );
//        projectRecyclerView.setAdapter(profileProjectListAdapter);
//
//        buttonNewProject = view.findViewById(R.id.buttonNewProject);
//        buttonNewProject.setOnClickListener(v -> {
//            profileViewModel.addProject(new Project("example", "i am"));
//            profileProjectListAdapter.notifyDataSetChanged();
//        });
//
//        textViewNumberOfProjects = view.findViewById(R.id.textViewNumberOfProjects);
//        profileViewModel.getProjectItemModels().observe(requireActivity(), projectItemModels -> {
//            Log.e("update", "data changed");
//            profileProjectListAdapter.updateList(projectItemModels);
//            profileProjectListAdapter.notifyDataSetChanged();
//            textViewNumberOfProjects.setText(String.valueOf(projectItemModels.size()));
//        });
        buttonRegisterAndLogIn = view.findViewById(R.id.buttonCreateAccountAndLogIn);
        buttonRegisterAndLogIn.setOnClickListener(v -> {

            authViewModel.register(new AuthModel(
                    editTextLogin.getText().toString(),
                    editTextPassword.getText().toString(),
                    ""));
        });
//            authRepo.setAuthModel(
//                    );

            //getActivity().getSupportFragmentManager()

            //switchToFragment(R.id.fragmentMainContainer, NavigationFragment.class, false);

//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentMainContainer, NavigationFragment.class, null)
//                    .commit();

//        profileViewModel.load();
    }
}