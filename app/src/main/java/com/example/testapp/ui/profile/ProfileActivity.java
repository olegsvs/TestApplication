package com.example.testapp.ui.profile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.example.testapp.App;
import com.example.testapp.R;
import com.example.testapp.core.BaseActivity;
import com.example.testapp.data.models.Post;
import com.example.testapp.databinding.ActivityProfileBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.ProfileComponent;
import com.example.testapp.di.modules.ProfileModule;
import com.example.testapp.data.models.Album;
import com.example.testapp.data.models.User;
import com.example.testapp.ui.photos.PhotoActivity;
import com.example.testapp.ui.photos.PhotoAdapter;
import com.example.testapp.ui.profile.album.ProfileAlbumView;
import com.example.testapp.ui.profile.post.ProfilePostView;
import com.example.testapp.utils.Const;

public class ProfileActivity extends BaseActivity<ActivityProfileBinding, ProfilePresenter> {

    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        initDaggerComponent();
        initAdapter();
        initView();
    }

    @Override
    protected void initDaggerComponent() {
        ProfileComponent component = App.getComponent().plus(new ProfileModule());
        if (component != null) {
            DaggerService.registerComponent(DaggerService.PROFILE_SCOPE_NAME, component);
            component.inject(this);
        }
    }

    private void initAdapter() {
        ProfilePageAdapter adapter = new ProfilePageAdapter();
        binding.tabLayout.setupWithViewPager(binding.profilePager);
        binding.profilePager.setAdapter(adapter);
        binding.profilePager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout)
        );
    }

    private void initView() {
        binding.toolbar.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initView(getIntent().getIntExtra(Const.USER_ID_KEY, 0));
    }

    void setData(User user) {
        binding.setModel(user);
    }

    void addAlbum(Album album) {
        ProfileAlbumView view = (ProfileAlbumView) binding.profilePager.getChildAt(0);
        view.addAlbum(album);
    }

    void addPost(Post post) {
        View view1 = binding.profilePager.getChildAt(1);
        ProfilePostView view = (ProfilePostView) view1;
        view.addPost(post);
    }

    void insertPost(Post post) {
        View view1 = binding.profilePager.getChildAt(1);
        ProfilePostView view = (ProfilePostView) view1;
        view.insertPost(post);
    }

    @Override
    public void onBackPressed() {
        DaggerService.unregisterComponent(DaggerService.PROFILE_SCOPE_NAME);
        super.onBackPressed();
    }

    void showAlbum(int albumId) {
        Intent intent = new Intent(ProfileActivity.this, PhotoActivity.class);
        intent.putExtra(Const.ALBUM_ID_KEY, albumId);
        startActivity(intent);
    }
}
