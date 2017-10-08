package com.example.testapp.di.modules;

import android.content.Context;

import dagger.Provides;

@dagger.Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext () {
        return context;
    }
}
