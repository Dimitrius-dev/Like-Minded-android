package com.dimitriusdev.fragments.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.fragments.base.ConfiguredFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ProfileFragment extends ConfiguredFragment {

    private AuthProvider authProvider;
    private ProfileViewModel profileViewModel;
    private RecyclerView projectRecyclerView;
    private ProfileProjectListAdapter profileProjectListAdapter;
    private List<ProjectModel> profileProjectModels;
    private TextView textViewNumberOfProjects;
    private TextView textProfileName;
    private Button buttonNewProject;
    private ImageButton buttonExit;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        authProvider = AuthProvider.getInstance();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("INIT", "ProfileFragment");

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        projectRecyclerView = view.findViewById(R.id.recycleViewProfileProjects);

        profileProjectModels = new LinkedList<>();
        profileProjectListAdapter = new ProfileProjectListAdapter(
                getContext(),
                new ArrayList<>()//profileViewModel.getProjectItemModels().getValue()
        );
        projectRecyclerView.setAdapter(profileProjectListAdapter);

        buttonNewProject = view.findViewById(R.id.buttonNewProject);
        buttonNewProject.setOnClickListener(v -> {
            switchToFragment(R.id.fragmentMenuContainer, ProjectFragment.class, true);
        });


        buttonExit = view.findViewById(R.id.imageButtonExit);
        buttonExit.setOnClickListener(v -> {
            authProvider.unauthorize();
        });

        textViewNumberOfProjects = view.findViewById(R.id.textViewNumberOfProjects);
        profileViewModel.getProjectItemModelsLiveData().observe(requireActivity(), projectItemModels -> {
            Log.i("CHECK", "changed");
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