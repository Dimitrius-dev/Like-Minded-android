package com.dimitriusdev.fragments.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProjectFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private EditText editTextProjectName;
    private EditText editTextProjectDescription;
    private Button buttonCreateProject;
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
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

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        editTextProjectName = view.findViewById(R.id.editTextProjectName);
        editTextProjectDescription = view.findViewById(R.id.editTextProjectDescription);
        buttonCreateProject = view.findViewById(R.id.buttonSaveAndCreateProject);

        buttonCreateProject.setOnClickListener(v -> {
            profileViewModel.addProject(new Project(
                    editTextProjectName.getText().toString(),
                    editTextProjectDescription.getText().toString()
            ));
        });



    }
}
