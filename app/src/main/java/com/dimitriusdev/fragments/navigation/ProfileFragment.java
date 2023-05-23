package com.dimitriusdev.fragments.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.AuthModel;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ProfileFragment extends ConfiguredFragment {
    private ProfileViewModel profileViewModel;
    private RecyclerView projectRecyclerView;
    private ProfileProjectListAdapter profileProjectListAdapter;
    private List<Project> profileProjects;
    private TextView textViewNumberOfProjects;
    private TextView textProfileName;
    private Button buttonNewProject;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("INIT", "ProfileFragment");

        projectRecyclerView = view.findViewById(R.id.recycleViewProfileProjects);

        profileProjects = new LinkedList<>();
        profileProjectListAdapter = new ProfileProjectListAdapter(
                getContext(),
                new ArrayList<>()//profileViewModel.getProjectItemModels().getValue()
        );
        projectRecyclerView.setAdapter(profileProjectListAdapter);

        buttonNewProject = view.findViewById(R.id.buttonNewProject);
        buttonNewProject.setOnClickListener(v -> {
            profileViewModel.addProject(new Project("example", "i am"));
            profileProjectListAdapter.notifyDataSetChanged();
        });

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        textViewNumberOfProjects = view.findViewById(R.id.textViewNumberOfProjects);
        profileViewModel.getProjectItemModelsLiveData().observe(requireActivity(), projectItemModels -> {
            profileProjectListAdapter.updateList(projectItemModels);
            profileProjectListAdapter.notifyDataSetChanged();
            textViewNumberOfProjects.setText(String.valueOf(projectItemModels.size()));
        });

        textProfileName = view.findViewById(R.id.textViewProfileName);
        profileViewModel.getAuthModelLiveData().observe(requireActivity(), authModel -> {
            textProfileName.setText(authModel.getLogin());
        });

        profileViewModel.load();
    }
}