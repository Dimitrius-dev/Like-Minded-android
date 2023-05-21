package com.dimitriusdev.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimitriusdev.adapters.SubsProjectListAdapter;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.SubsViewModel;

import java.util.ArrayList;
import java.util.List;


public class SubsFragment extends Fragment {

    private SubsViewModel subsViewModel;
    private RecyclerView subsRecyclerView;

    private SubsProjectListAdapter subsProjectListAdapter;
    private List<Project> searchProjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subsViewModel = new ViewModelProvider(requireActivity()).get(SubsViewModel.class);
        subsRecyclerView = view.findViewById(R.id.recyclerViewSubsProjects);

        Log.i("INIT", "ProfileFragment");

        subsRecyclerView = view.findViewById(R.id.recyclerViewSubsProjects); // ---

        searchProjects = new ArrayList<>();
        subsProjectListAdapter = new SubsProjectListAdapter(
                getContext(),
                new ArrayList<>()//profileViewModel.getProjectItemModels().getValue()
        );
        subsRecyclerView.setAdapter(subsProjectListAdapter);


        subsViewModel.getProjectItemModels().observe(requireActivity(), projectItemModels -> {
            Log.e("update", "data changed");
            subsProjectListAdapter.updateList(projectItemModels);
            subsProjectListAdapter.notifyDataSetChanged();
        });

        subsViewModel.load();
    }
}