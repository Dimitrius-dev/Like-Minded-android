package com.dimitriusdev.fragments.navigation;

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

import com.dimitriusdev.adapters.SearchProjectListAdapter;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;
import com.dimitriusdev.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;


public class SearchListFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private RecyclerView searchRecyclerView;

    private SearchProjectListAdapter searchProjectListAdapter;
    private List<Project> searchProjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        searchProjects = new ArrayList<>();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        searchRecyclerView = view.findViewById(R.id.recyclerViewSearchProjects);

        Log.i("INIT", "ProfileFragment");

        searchProjectListAdapter = new SearchProjectListAdapter(
                getContext(),
                new ArrayList<>()//profileViewModel.getProjectItemModels().getValue()
        );
        searchRecyclerView.setAdapter(searchProjectListAdapter);

        searchViewModel.getProjectItemModels().observe(requireActivity(), projectItemModels -> {
            Log.e("update", "data changed");
            searchProjectListAdapter.updateList(projectItemModels);
            searchProjectListAdapter.notifyDataSetChanged();
        });

        searchViewModel.load();
    }
}