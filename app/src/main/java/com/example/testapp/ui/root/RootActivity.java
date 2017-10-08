package com.example.testapp.ui.root;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.testapp.App;
import com.example.testapp.R;
import com.example.testapp.core.BaseActivity;
import com.example.testapp.data.models.User;
import com.example.testapp.databinding.ActivityRootBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.RootComponent;
import com.example.testapp.di.modules.RootModule;
import com.example.testapp.ui.profile.ProfileActivity;
import com.example.testapp.utils.Const;

public class RootActivity extends BaseActivity<ActivityRootBinding, RootPresenter> {

    private RootAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root);
        initDaggerComponent();

        initView();
    }

    @Override
    protected void initDaggerComponent() {
        RootComponent component = App.getComponent().plus(new RootModule());
        if (component != null) {
            DaggerService.registerComponent(DaggerService.ROOT_SCOPE_NAME, component);
            component.inject(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initView();
    }

    private void initView() {
        adapter = new RootAdapter();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        binding.rootUserList.setLayoutManager(llm);
        binding.rootUserList.setHasFixedSize(true);
        binding.rootUserList.setAdapter(adapter);
    }

    void addItem(User user) {
        adapter.addItem(user);
    }

    void showProfile(int userId) {
        Intent intent = new Intent(RootActivity.this, ProfileActivity.class);
        intent.putExtra(Const.USER_ID_KEY, userId);
        startActivity(intent);
    }
}
