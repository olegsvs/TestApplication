package com.example.testapp;

import android.app.Application;
import android.content.Context;

import com.example.testapp.di.components.AppComponent;
import com.example.testapp.di.components.DaggerAppComponent;
import com.example.testapp.di.components.DataComponent;
import com.example.testapp.di.modules.AppModule;
import com.example.testapp.di.modules.DataModule;

public class App extends Application{

    private static Context context;
    private static DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initDaggerComponents();
    }

    public static Context getAppContext() {
        return context;
    }

    public static DataComponent getComponent() {
        return dataComponent;
    }

    //region ================= DI =================

    private void initDaggerComponents() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
        dataComponent = appComponent.plus(new DataModule());
    }

    //endregion
}
