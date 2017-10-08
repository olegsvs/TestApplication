package com.example.testapp.ui.profile.post;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.databinding.PostItemBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.ProfileComponent;
import com.example.testapp.data.models.Post;
import com.example.testapp.ui.profile.ProfilePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ItemHolder>{

    private List<Post> items;

    @Inject
    ProfilePresenter presenter;

    public ProfilePostAdapter() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.getBinding().setModel(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void addItem(Post post) {
        items.add(post);
        notifyDataSetChanged();
    }

    public void insertItem(Post post) {
        items.add(0, post);
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private final PostItemBinding binding;

        public ItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public PostItemBinding getBinding() {
            return binding;
        }
    }
}
