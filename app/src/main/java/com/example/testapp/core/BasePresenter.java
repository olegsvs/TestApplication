package com.example.testapp.core;

import com.example.testapp.data.Repository;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.DataComponent;

import javax.inject.Inject;

public abstract class BasePresenter<V extends BaseActivity> {

    private V view;

    @Inject
    protected Repository repository;

    public BasePresenter() {
        initDaggerComponent();
    }

    protected abstract void initDaggerComponent();

    protected V getView() {
        return view;
    }

    protected void takeView(V view) {
        this.view = view;
    }

    protected void dropView() {
        this.view = null;
    }
}
