package com.example.testapp.ui.profile.post;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.testapp.data.models.Post;
import com.example.testapp.databinding.ProfilePostsBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.ProfileComponent;
import com.example.testapp.ui.profile.ProfilePresenter;

import javax.inject.Inject;

public class ProfilePostView extends RelativeLayout {

    private ProfilePostAdapter adapter;
    private ProfilePostsBinding binding;

    @Inject
    ProfilePresenter presenter;

    public ProfilePostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            adapter = new ProfilePostAdapter();
            initDaggerComponent();
        }
    }

    private void initDaggerComponent() {
        ProfileComponent component = DaggerService.getComponent(DaggerService.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            binding = DataBindingUtil.bind(this);

            LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
            binding.userPostList.setLayoutManager(llm);
            binding.userPostList.setHasFixedSize(true);
            binding.userPostList.setAdapter(adapter);

            binding.addPostFab.setOnClickListener(view -> presenter.addPostClick());
        }
    }

    public void addPost(Post post) {
        adapter.addItem(post);
    }

    public void insertPost(Post post) {
        adapter.insertItem(post);
    }
}
