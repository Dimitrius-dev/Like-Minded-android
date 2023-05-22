package com.dimitriusdev.dialog;

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


public final class ProjectInfoDialog extends DialogFragment {

    private String name;
    private String description;

    public ProjectInfoDialog(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.dialog_info_project, container, false);

        TextView projectName = root.findViewById(R.id.dialogeProjectName);
        projectName.setText(name);

        TextView projectDescription = root.findViewById(R.id.dialogeProjectInfo);
        projectDescription.setText(description);

        Button button = root.findViewById(R.id.buttonCloseProjectInfo);
        button.setOnClickListener(v -> {
            dismiss();
        });

        return root;
    }
}