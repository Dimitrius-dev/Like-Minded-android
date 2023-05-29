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
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.List;

public class ProfileProjectListAdapter extends RecyclerView.Adapter<ProfileProjectListAdapter.ViewHolder> {

    private ProfileViewModel profileViewModel;
    private final LayoutInflater layoutInflater;
    private List<ProjectModel> projectModelItemModels;

    public ProfileProjectListAdapter(Context context, List<ProjectModel> projectModelList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.projectModelItemModels = projectModelList;

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
        ProjectModel projectModelItemModel = projectModelItemModels.get(position);
        holder.projectName.setText(projectModelItemModel.getName() + " " + position + " " + projectModelItemModel.getDescription());
        //holder.projectAuthor.setText(projectModelItemModel.getAuthorCustomer().getLogin());

        holder.imageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            profileViewModel.removeProject(projectModelItemModel);
            //notifyItemRemoved(position);
            //notifyItemRangeChanged(0, projectModelItemModels.size());
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
