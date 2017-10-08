package com.example.testapp.di.modules;

import com.example.testapp.data.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Provides
    @Singleton
    Repository provideRepository() {
        return new Repository();
    }
}
