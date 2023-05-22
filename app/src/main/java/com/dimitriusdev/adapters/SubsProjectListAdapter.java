package com.dimitriusdev.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.Project;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.api.SubsApi;
import com.dimitriusdev.viewmodels.ProfileViewModel;
import com.dimitriusdev.viewmodels.SubsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsProjectListAdapter extends RecyclerView.Adapter<SubsProjectListAdapter.ViewHolder> {

    private SubsApi subsApi;
    private AuthProvider authProvider;
    private SubsViewModel subsViewModel;
    private final LayoutInflater layoutInflater;
    private List<Project> projectItemModels;

    public SubsProjectListAdapter(Context context, List<Project> projectList) {
        this.subsApi = new SubsApi();
        this.authProvider = AuthProvider.getInstance();

        this.layoutInflater = LayoutInflater.from(context);
        this.projectItemModels = projectList;

        this.subsViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SubsViewModel.class);
    }
//
    public void updateList(List<Project> newProjectItemModels){
        projectItemModels = newProjectItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.project_item_sub, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = projectItemModels.get(position);
        holder.projectName.setText(project.getName() + " " + String.valueOf(position) + " " + project.getDescription());
        holder.projectAuthor.setText(project.getAuthorCustomer().getLogin());

        holder.unsubImageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            subsApi.createRequest()
                    .unsubscribeOnSub(authProvider.getAuthModel().getLogin(), project.getName())
                    .enqueue(new Callback<>() {
                        @Override
                        public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                            Log.i("RESPONSE removeProject", String.valueOf(response.code()));
                            if (response.code() == 200) {
                                subsViewModel.removeProject(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(0, projectItemModels.size());
                                return;
                            }
                        }
                        @Override
                        public void onFailure(Call<MsgModel> call, Throwable t) {
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
        final ImageButton unsubImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.project_name_sub);
            projectAuthor = itemView.findViewById(R.id.project_author_sub);
            unsubImageButton = itemView.findViewById(R.id.unsub);
        }
    }
}
