package com.example.testapp.ui.new_post;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.data.models.NewPost;
import com.example.testapp.data.models.Post;

import io.reactivex.Observable;

public class NewPostDialog extends AlertDialog {


    protected NewPostDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static Observable<NewPost> showDialog(Context context, int userId) {
        return Observable.create(emitter -> {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_new_post, null);

            NewPostDialog dialog = new NewPostDialog(context, R.style.NoTitleBarFullscreen);

            view.findViewById(R.id.cross_back).setOnClickListener(v -> {
                dialog.dismiss();
                emitter.onNext(new NewPost(userId, "", ""));
                dialog.dismiss();
            });
            view.findViewById(R.id.save_post).setOnClickListener(v -> {
                String title = ((TextView) view.findViewById(R.id.post_title)).getText().toString();
                String body =  ((TextView) view.findViewById(R.id.post_body)).getText().toString();
                dialog.dismiss();
                emitter.onNext(new NewPost(userId, title, body));
            });

            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);

            dialog.show();
        });
    }
}
