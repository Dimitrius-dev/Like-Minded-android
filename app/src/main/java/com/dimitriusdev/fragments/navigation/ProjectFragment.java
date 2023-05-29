package com.dimitriusdev.fragments.navigation;

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
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.CustomerModel;
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.viewmodels.ProjectViewModel;

public class ProjectFragment extends ConfiguredFragment {

    private ProjectViewModel projectViewModel;
    private EditText editTextProjectName;
    private EditText editTextProjectDescription;
    private Button buttonCreateProject;

    private AuthRepo authRepo;
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        authRepo = AuthRepo.getInstance(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("INIT", "ProfileFragment");

        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        editTextProjectName = view.findViewById(R.id.editTextProjectName);
        editTextProjectDescription = view.findViewById(R.id.editTextProjectDescription);
        buttonCreateProject = view.findViewById(R.id.buttonSaveAndCreateProject);

        buttonCreateProject.setOnClickListener(v -> {
            projectViewModel.createProject(new ProjectModel(
                    editTextProjectName.getText().toString(),
                    editTextProjectDescription.getText().toString()
            ));
            switchToFragment(R.id.fragmentMenuContainer, ProfileFragment.class, false);

        });

        projectViewModel.getProjectModel().observe(requireActivity(), project -> {
            Log.i("CHECK", "start 2");
            switchToFragment(R.id.fragmentMenuContainer, ProfileFragment.class, false);
        });

    }
}
