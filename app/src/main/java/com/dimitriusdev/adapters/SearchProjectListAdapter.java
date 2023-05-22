package com.dimitriusdev.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.api.SearchApi;
import com.dimitriusdev.viewmodels.ProfileViewModel;
import com.dimitriusdev.viewmodels.SearchViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProjectListAdapter extends RecyclerView.Adapter<SearchProjectListAdapter.ViewHolder> {

    private SearchApi searchApi;
    private AuthProvider authProvider;
    private SearchViewModel searchViewModel;
    private final LayoutInflater layoutInflater;
    private List<Project> projectItemModels;

    public SearchProjectListAdapter(Context context, List<Project> projectList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.projectItemModels = projectList;

        this.authProvider = AuthProvider.getInstance();
        this.searchApi = new SearchApi();

        this.searchViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SearchViewModel.class);
    }
    //
    public void updateList(List<Project> newProjectItemModels){
        projectItemModels = newProjectItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.project_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = projectItemModels.get(position);
        holder.projectName.setText(project.getName() + " " + String.valueOf(position) + " " + project.getDescription());
        holder.projectAuthor.setText(project.getAuthorCustomer().getLogin());

        holder.imageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            searchApi.createRequest()
                    .subscribeOnProject(authProvider.getAuthModel().getLogin(), project.getName()).enqueue(new Callback<MsgModel>() {
                        @Override
                        public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                            Log.i("RESPONSE subscribeOnProject", String.valueOf(response.code()));

                            if(response.code() == 200){
                                Toast.makeText(layoutInflater.getContext(), "sibscribe", Toast.LENGTH_SHORT).show();
                            }
//                            searchViewModel.removeProject(position);
//                            notifyItemChanged(position);
//                            notifyItemRangeChanged(0, projectItemModels.size());
                        }
                        @Override
                        public void onFailure(Call<MsgModel> call, Throwable t) {
                            Toast.makeText(layoutInflater.getContext(), "connection error", Toast.LENGTH_SHORT).show();
                            Log.i("INTERNET ERROR", t.toString());
                        }
                    });


        });
    }

    @Override
    public int getItemCount() {
        return projectItemModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView projectName;
        final TextView projectAuthor;
        final ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.project_name_search);
            projectAuthor = itemView.findViewById(R.id.project_author_search);
            imageButton = itemView.findViewById(R.id.join_project);
        }
    }
}
