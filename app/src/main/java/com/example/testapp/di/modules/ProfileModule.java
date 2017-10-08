package com.example.testapp.di.modules;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.ui.profile.ProfileActivity;
import com.example.testapp.ui.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {
    @Provides
    @DaggerScope(ProfileActivity.class)
    ProfilePresenter provideProfilePresenter() {
        return new ProfilePresenter();
    }
}
