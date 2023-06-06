package com.dimitriusdev.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.models.ProjectModel;


public final class ProjectInfoDialog extends DialogFragment {

    private ProjectModel projectModel;

    public ProjectInfoDialog(ProjectModel projectModel){
        this.projectModel = projectModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.dialog_info_project, container, false);

        //root.getRootView().setStyle
        //this.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        //root.setScrollBarStyle(android.R.color.transparent);

        //root.setBackground(new ColorDrawable(Color.TRANSPARENT));

        TextView projectName = root.findViewById(R.id.dialogeProjectName);
        projectName.setText(projectModel.getName());

        TextView projectDescription = root.findViewById(R.id.dialogeProjectInfo);
        projectDescription.setText(projectModel.getDescription());

        Button button = root.findViewById(R.id.buttonCloseProjectInfo);
        button.setOnClickListener(v -> {
            dismiss();
        });

        return root;
    }
}
