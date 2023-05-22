package com.dimitriusdev.fragments.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimitriusdev.fragments.AuthFragment;
import com.dimitriusdev.fragments.base.SwitchFragment;
import com.dimitriusdev.likeminded.R;
import com.dimitriusdev.providers.AuthProvider;

public class ConfiguredFragment extends SwitchFragment {

//    protected AuthProvider authProvider;

    public ConfiguredFragment() {
        super(R.id.fragmentMainContainer, AuthFragment.class);
//        authProvider = AuthProvider.getInstance(getContext());
    }

}
