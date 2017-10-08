package com.example.testapp.ui.root;

import com.example.testapp.core.BasePresenter;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.RootComponent;

public class RootPresenter extends BasePresenter<RootActivity> {

    @Override
    protected void initDaggerComponent() {
        RootComponent component = DaggerService.getComponent(DaggerService.ROOT_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    protected void initView() {
        repository.getUsers()
                .subscribe(
                        userRes -> {
                            if (getView() != null)
                                getView().addItem(userRes);
                        }
                );
    }

    void onItemClick(int userId) {
        if (getView() != null)
            getView().showProfile(userId);
    }
}
