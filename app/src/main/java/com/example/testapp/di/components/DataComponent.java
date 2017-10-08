package com.example.testapp.di.components;

import com.example.testapp.data.Repository;
import com.example.testapp.di.modules.DataModule;
import com.example.testapp.di.modules.NetworkModule;
import com.example.testapp.di.modules.PhotoModule;
import com.example.testapp.di.modules.ProfileModule;
import com.example.testapp.di.modules.RootModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Subcomponent(modules = {NetworkModule.class, DataModule.class})
@Singleton
public interface DataComponent {
    void inject(Repository repository);

    RootComponent plus(RootModule module);
    ProfileComponent plus(ProfileModule module);
    PhotoComponent plus(PhotoModule module);
}