package com.dimitriusdev.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.dimitriusdev.dialog.ProjectInfoDialog;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.MsgModel;
import com.dimitriusdev.models.ProjectModel;
import com.dimitriusdev.repository.AuthRepo;
import com.dimitriusdev.repository.api.SearchApi;
import com.dimitriusdev.viewmodels.SearchViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProjectListAdapter extends RecyclerView.Adapter<SearchProjectListAdapter.ViewHolder> {

    private SearchApi searchApi;
    private AuthRepo authRepo;
    private SearchViewModel searchViewModel;
    private final LayoutInflater layoutInflater;
    private List<ProjectModel> projectModelItemModels;

    public SearchProjectListAdapter(Context context, List<ProjectModel> projectModelList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.projectModelItemModels = projectModelList;

        this.authRepo = AuthRepo.getInstance(context);
        this.searchApi = new SearchApi();

        this.searchViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SearchViewModel.class);
    }
    //
    public void updateList(List<ProjectModel> newProjectItemModelModels){
        projectModelItemModels = newProjectItemModelModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.project_item_search, parent, false);

//        view.setOnClickListener(v -> {
//            ProjectInfoDialog projectInfoDialog = new ProjectInfoDialog()
//        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectModel projectModel = projectModelItemModels.get(position);


        holder.itemView.setOnClickListener(v -> {

            ProjectInfoDialog projectInfoDialog = new ProjectInfoDialog(projectModel);
            //projectInfoDialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// .get.get .getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            //projectInfoDialog.getView().setBackgroundResource(android.R.color.transparent);
            //projectInfoDialog.setStyle(DialogFragment.STYLE_NORMAL, 0); // STYLE_NO_FRAME
            //projectInfoDialog.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
            projectInfoDialog.show(
                    ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager(),
                    null);
        });

        if(projectModel.getAuthorCustomer().getLogin().equals(authRepo.getAuthModel().getLogin())){
            holder.imageButton.setVisibility(View.GONE);
        }

        holder.projectName.setText(projectModel.getName());
        //Log.i("VIEW", projectModel.getAuthorCustomer().getLogin());
        holder.projectAuthor.setText(projectModel.getAuthorCustomer().getLogin());

        holder.imageButton.setOnClickListener(v -> {
            Log.d("CHECK", String.valueOf(position));

            searchApi.createRequest()
                    .subscribeOnProject(authRepo.getAuthModel().getToken(),
                            authRepo.getAuthModel().getLogin(), projectModel.getName()).enqueue(new Callback<MsgModel>() {
                        @Override
                        public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {

                            if(response.code() == 200){
                                Toast.makeText(layoutInflater.getContext(), "sibscribe", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(layoutInflater.getContext(), "logic error", Toast.LENGTH_SHORT).show();
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
        return projectModelItemModels.size();
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
