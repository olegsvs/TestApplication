package com.example.testapp.di.modules;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.ui.root.RootActivity;
import com.example.testapp.ui.root.RootPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class RootModule {
    @Provides
    @DaggerScope(RootActivity.class)
    RootPresenter provideRootPresenter() {
        return new RootPresenter();
    }
}
