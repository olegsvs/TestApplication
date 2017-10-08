package com.example.testapp.utils;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView view, String imageUrl) {
        if (view == null || TextUtils.isEmpty(imageUrl))
            return;

        Glide.with(view.getContext())
                .load(imageUrl)
                .fitCenter()
                .centerCrop()
                .dontAnimate()
                .into(view);
    }
}
