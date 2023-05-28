package com.dimitriusdev.fragments.base;

import com.dimitriusdev.fragments.AuthFragment;
import com.dimitriusdev.likeminded.R;

public class ConfiguredFragment extends SwitchFragment {

//    protected AuthProvider authProvider;

    public ConfiguredFragment() {
        super(R.id.fragmentMainContainer, AuthFragment.class);
//        authProvider = AuthProvider.getInstance(getContext());
    }

}
