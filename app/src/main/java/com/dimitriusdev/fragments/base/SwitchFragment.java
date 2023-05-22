package com.dimitriusdev.fragments.base;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class SwitchFragment extends Fragment {

    private int rootContainerViewId;
    private Class<? extends Fragment> rootFragmentClass;

    public SwitchFragment(@IdRes int rootContainerViewId, @NotNull Class<? extends Fragment> rootFragmentClass){
        super();
        this.rootContainerViewId = rootContainerViewId;
        this.rootFragmentClass = rootFragmentClass;
    }

    public void switchToFragment(@IdRes int containerViewId, @NotNull Class<? extends Fragment> fragmentClass, boolean addToBackStack){
        if(addToBackStack){
            getActivity().getSupportFragmentManager().beginTransaction()
            //getChildFragmentManager().beginTransaction()
                    .replace(containerViewId, fragmentClass, null)
                    .addToBackStack(null)
                    .commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction()
            //getChildFragmentManager().beginTransaction()
                    .replace(containerViewId, fragmentClass, null)
                    .commit();
        }
    }

    public void switchToRoot(){
        getActivity().getSupportFragmentManager().beginTransaction()
//        getChildFragmentManager().beginTransaction()
                .replace(rootContainerViewId, rootFragmentClass, null)
                .commit();
    }
}
