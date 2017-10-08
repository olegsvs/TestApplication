package com.example.testapp.di.components;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.di.modules.ProfileModule;
import com.example.testapp.ui.profile.ProfileActivity;
import com.example.testapp.ui.profile.album.ProfileAlbumAdapter;
import com.example.testapp.ui.profile.post.ProfilePostAdapter;
import com.example.testapp.ui.profile.ProfilePresenter;
import com.example.testapp.ui.profile.post.ProfilePostView;

import dagger.Subcomponent;

@Subcomponent(modules = ProfileModule.class)
@DaggerScope(ProfileActivity.class)
public interface ProfileComponent {
    void inject(ProfileActivity activity);
    void inject(ProfilePresenter presenter);
    void inject(ProfileAlbumAdapter adapter);
    void inject(ProfilePostAdapter adapter);
    void inject(ProfilePostView view);
}
