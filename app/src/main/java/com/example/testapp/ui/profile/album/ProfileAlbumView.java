package com.example.testapp.ui.profile.album;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.testapp.data.models.Album;
import com.example.testapp.databinding.ProfileAlbumsBinding;

public class ProfileAlbumView extends RelativeLayout {

    private ProfileAlbumAdapter adapter;
    private ProfileAlbumsBinding binding;

    public ProfileAlbumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()){
            adapter = new ProfileAlbumAdapter();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            binding = DataBindingUtil.bind(this);

            LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
            binding.userAlbumList.setLayoutManager(llm);
            binding.userAlbumList.setHasFixedSize(true);
            binding.userAlbumList.setAdapter(adapter);
        }
    }

    public void addAlbum(Album album) {
        adapter.addItem(album);
    }
}
