package com.example.testapp.di.components;

import android.content.Context;

import com.example.testapp.di.modules.AppModule;
import com.example.testapp.di.modules.DataModule;

@dagger.Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();

    DataComponent plus(DataModule module);
}
