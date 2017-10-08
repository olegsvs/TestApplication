package com.example.testapp.di.modules;

import com.example.testapp.di.DaggerScope;
import com.example.testapp.ui.photos.PhotoActivity;
import com.example.testapp.ui.photos.PhotoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoModule {
    @Provides
    @DaggerScope(PhotoActivity.class)
    PhotoPresenter providePhotoPresenter() {
       return new PhotoPresenter();
    }
}
