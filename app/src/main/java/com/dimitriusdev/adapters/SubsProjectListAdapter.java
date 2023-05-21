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
import com.dimitriusdev.models.Project;
import com.dimitriusdev.viewmodels.ProfileViewModel;

import java.util.List;

public class SubsProjectListAdapter extends RecyclerView.Adapter<SubsProjectListAdapter.ViewHolder> {

    private ProfileViewModel profileViewModel;
    private final LayoutInflater layoutInflater;
    private List<Project> projectItemModels;

    public SubsProjectListAdapter(Context context, List<Project> projectList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.projectItemModels = projectList;

        this.profileViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProfileViewModel.class);
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
        Project projectItemModel = projectItemModels.get(position);
        holder.projectName.setText(projectItemModel.getName() + " " + String.valueOf(position) + " " + projectItemModel.getDescription());
        holder.projectAuthor.setText(projectItemModel.getAuthorCustomer().getLogin());

        holder.imageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            profileViewModel.removeProject(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, projectItemModels.size());
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

            projectName = itemView.findViewById(R.id.project_name_sub);
            projectAuthor = itemView.findViewById(R.id.project_author_sub);
            imageButton = itemView.findViewById(R.id.unsub);
        }
    }
}
