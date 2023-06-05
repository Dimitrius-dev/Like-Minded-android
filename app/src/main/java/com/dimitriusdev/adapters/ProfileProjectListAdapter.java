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
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.providers.AuthProvider;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.ProfileApi;
import com.dimitriusdev.viewmodels.ProfileViewModel;
import com.dimitriusdev.viewmodels.SubsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileProjectListAdapter extends RecyclerView.Adapter<ProfileProjectListAdapter.ViewHolder> {

    private ProfileViewModel profileViewModel;
    private final LayoutInflater layoutInflater;
    private final AuthRepo authRepo;
    private AuthProvider authProvider;

    private List<ProjectModel> projectModelItemModels;

    public ProfileProjectListAdapter(Context context, List<ProjectModel> projectModelList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.projectModelItemModels = projectModelList;
        this.authRepo = AuthRepo.getInstance(context);
        this.authProvider = AuthProvider.getInstance();
        this.profileViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProfileViewModel.class);

    }
//
    public void updateList(List<ProjectModel> newProjectItemModelModels){
        projectModelItemModels = newProjectItemModelModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.project_item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectModel projectModel = projectModelItemModels.get(position);
        holder.projectName.setText(projectModel.getName());
        //holder.projectAuthor.setText(projectModelItemModel.getAuthorCustomer().getLogin());

        holder.imageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            projectModelItemModels.remove(projectModel);
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, projectModelItemModels.size());

            new ProfileApi().createRequest().removeProject(authRepo.getAuthModel().getToken(),
                    authRepo.getAuthModel().getLogin(),
                    projectModel.getName()).enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                    Log.i("CHECK", String.valueOf(response.code()));
                    if (response.code() == 200) {
                        projectModelItemModels.remove(projectModel);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, projectModelItemModels.size());
                    } else if (response.code() == 401) {
                        authProvider.unauthorize();
                    } else {
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
        return projectModelItemModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView projectName;
        final TextView projectAuthor;
        final ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.project_name);
            projectAuthor = itemView.findViewById(R.id.project_author);
            imageButton = itemView.findViewById(R.id.delete_project);
        }
    }
}
