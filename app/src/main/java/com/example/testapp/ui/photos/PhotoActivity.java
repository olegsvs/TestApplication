package com.example.testapp.ui.photos;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.testapp.App;
import com.example.testapp.R;
import com.example.testapp.core.BaseActivity;
import com.example.testapp.data.models.Photo;
import com.example.testapp.databinding.ActivityPhotoBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.PhotoComponent;
import com.example.testapp.di.modules.PhotoModule;
import com.example.testapp.utils.Const;

public class PhotoActivity extends BaseActivity<ActivityPhotoBinding, PhotoPresenter> {

    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo);

        initDaggerComponent();
        initAdapter();
        initView();
    }

    @Override
    protected void initDaggerComponent() {
        PhotoComponent component = App.getComponent().plus(new PhotoModule());
        if (component != null) {
            DaggerService.registerComponent(DaggerService.PHOTO_SCOPE_NAME, component);
            component.inject(this);
        }
    }

    private void initAdapter() {
        adapter = new PhotoAdapter();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        binding.photoList.setLayoutManager(llm);
        binding.photoList.setHasFixedSize(true);
        binding.photoList.setAdapter(adapter);
    }

    private void initView() {
        binding.toolbar.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initView(getIntent().getIntExtra(Const.ALBUM_ID_KEY, 0));
    }

    void addPhoto(Photo photo) {
        adapter.addItem(photo);
    }

    @Override
    public void onBackPressed() {
        DaggerService.unregisterComponent(DaggerService.PHOTO_SCOPE_NAME);
        super.onBackPressed();
    }
}
