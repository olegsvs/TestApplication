package com.example.testapp.di.components;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.di.modules.RootModule;
import com.example.testapp.ui.root.RootActivity;
import com.example.testapp.ui.root.RootAdapter;
import com.example.testapp.ui.root.RootPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = RootModule.class)
@DaggerScope(RootActivity.class)
public interface RootComponent {
    void inject(RootActivity activity);
    void inject(RootPresenter presenter);
    void inject(RootAdapter adapter);
}
