package com.example.testapp.ui.root;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.data.models.User;
import com.example.testapp.databinding.UserItemBinding;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.RootComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RootAdapter extends RecyclerView.Adapter<RootAdapter.ItemHolder>{

    private List<User> items;

    @Inject
    RootPresenter presenter;

    public RootAdapter() {
        this.items = new ArrayList<>();
        initDaggerComponent();
    }

    void initDaggerComponent() {
        RootComponent component = DaggerService.getComponent(DaggerService.ROOT_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        User item = items.get(position);
        holder.getBinding().setModel(item);
        holder.getBinding().userLayout.setOnClickListener(
                view -> presenter.onItemClick(item.getId())
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void addItem(User user) {
        items.add(user);
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private final UserItemBinding binding;

        public ItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public UserItemBinding getBinding() {
            return binding;
        }
    }
}
