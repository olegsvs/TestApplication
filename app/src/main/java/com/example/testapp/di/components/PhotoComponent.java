package com.example.testapp.di.components;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.di.modules.PhotoModule;
import com.example.testapp.ui.photos.PhotoActivity;
import com.example.testapp.ui.photos.PhotoPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = PhotoModule.class)
@DaggerScope(PhotoActivity.class)
public interface PhotoComponent {
    void inject(PhotoActivity activity);
    void inject(PhotoPresenter presenter);
}
