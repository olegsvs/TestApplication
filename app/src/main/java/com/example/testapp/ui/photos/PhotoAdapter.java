package com.example.testapp.ui.photos;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.data.models.Photo;
import com.example.testapp.databinding.PhotoItemBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.PhotoComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ItemHolder> {

    private List<Photo> items;

    public PhotoAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
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

    public void addItem(Photo photo) {
        items.add(photo);
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private final PhotoItemBinding binding;

        public ItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public PhotoItemBinding getBinding() {
            return binding;
        }
    }
}
