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
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.SubsApi;
import com.dimitriusdev.viewmodels.SubsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubsProjectListAdapter extends RecyclerView.Adapter<SubsProjectListAdapter.ViewHolder> {

    private SubsApi subsApi;
    private AuthRepo authRepo;
    private SubsViewModel subsViewModel;
    private final LayoutInflater layoutInflater;
    private List<ProjectModel> projectModelItemModels;

    public SubsProjectListAdapter(Context context, List<ProjectModel> projectModelList) {
        this.subsApi = new SubsApi();

        this.layoutInflater = LayoutInflater.from(context);
        this.projectModelItemModels = projectModelList;

        this.authRepo = AuthRepo.getInstance(context);

        this.subsViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SubsViewModel.class);
    }
//
    public void updateList(List<ProjectModel> newProjectItemModelModels){
        projectModelItemModels = newProjectItemModelModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.project_item_sub, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectModel projectModel = projectModelItemModels.get(position);
        holder.projectName.setText(projectModel.getName() + " " + String.valueOf(position) + " " + projectModel.getDescription());
        holder.projectAuthor.setText(projectModel.getAuthorCustomer().getLogin());

        holder.unsubImageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            subsApi.createRequest()
                    .unsubscribeOnSub(authRepo.getAuthModel().getToken(),
                            authRepo.getAuthModel().getLogin(), projectModel.getName())
                    .enqueue(new Callback<>() {
                        @Override
                        public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                            Log.i("RESPONSE removeProject", String.valueOf(response.code()));
                            if (response.code() == 200) {
                                subsViewModel.removeProject(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(0, projectModelItemModels.size());
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
        return projectModelItemModels.size();
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
