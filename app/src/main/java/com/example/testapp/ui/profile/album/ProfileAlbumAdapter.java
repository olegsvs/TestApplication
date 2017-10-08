package com.example.testapp.ui.profile.album;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.data.models.Album;
import com.example.testapp.databinding.AlbumItemBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.ProfileComponent;
import com.example.testapp.ui.profile.ProfilePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProfileAlbumAdapter extends RecyclerView.Adapter<ProfileAlbumAdapter.ItemHolder>{

    private List<Album> items;

    @Inject
    ProfilePresenter presenter;

    public ProfileAlbumAdapter() {
        items = new ArrayList<>();
        initDaggerComponent();
    }

    private void initDaggerComponent() {
        ProfileComponent component = DaggerService.getComponent(DaggerService.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Album album = items.get(position);
        holder.getBinding().setModel(album);
        holder.getBinding().albumLayout.setOnClickListener(
                view -> presenter.albumClick(album.getId())
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void addItem(Album album) {
        items.add(album);
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private final AlbumItemBinding binding;

        public ItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public AlbumItemBinding getBinding() {
            return binding;
        }
    }
}
