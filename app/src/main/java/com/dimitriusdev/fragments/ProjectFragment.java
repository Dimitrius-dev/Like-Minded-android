package com.dimitriusdev.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.adapters.ProfileProjectListAdapter;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ProjectFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    private RecyclerView projectRecyclerView;
    private ProfileProjectListAdapter profileProjectListAdapter;
    private List<Project> profileProjects;
    private TextView textViewNumberOfProjects;

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

//
//        textViewNumberOfProjects = view.findViewById(R.id.textViewNumberOfProjects);
//        profileViewModel.getProjectItemModels().observe(requireActivity(), projectItemModels -> {
//            Log.e("update", "data changed");
//            profileProjectListAdapter.updateList(projectItemModels);
//            profileProjectListAdapter.notifyDataSetChanged();
//            textViewNumberOfProjects.setText(String.valueOf(projectItemModels.size()));
//        });
//
//        profileViewModel.load();
    }
}