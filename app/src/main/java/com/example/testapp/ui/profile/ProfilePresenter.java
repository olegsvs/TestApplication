package com.example.testapp.ui.profile;

import android.text.TextUtils;

import com.example.testapp.core.BasePresenter;
import com.example.testapp.data.models.NewPost;
import com.example.testapp.di.DaggerService;
import com.example.testapp.di.components.ProfileComponent;
import com.example.testapp.ui.new_post.NewPostDialog;

public class ProfilePresenter extends BasePresenter<ProfileActivity> {

    private int userId;

    @Override
    protected void initDaggerComponent() {
        ProfileComponent component = DaggerService.getComponent(DaggerService.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    void initView(int userId) {
        if (getView() != null) {
            this.userId = userId;

            repository.getUserById(userId)
                    .subscribe(
                            user -> getView().setData(user),
                            throwable -> getView().showMessage(throwable.getMessage())
                    );

            repository.getUserAlbums(userId)
                    .subscribe(
                            album -> getView().addAlbum(album),
                            throwable -> getView().showMessage(throwable.getMessage())
                    );

            repository.getUserPosts(userId)
                    .subscribe(
                            post -> getView().addPost(post),
                            throwable -> getView().showMessage(throwable.getMessage())
                    );
        }
    }

    public void albumClick(int albumId) {
        if (getView() != null)
            getView().showAlbum(albumId);
    }

    public void addPostClick(){
        if (getView() != null)
            NewPostDialog.showDialog(getView(), userId)
                    .subscribe(
                            newPost -> {
                                if (!TextUtils.isEmpty(newPost.getTitle()) &&
                                        !TextUtils.isEmpty(newPost.getBody()))
                                    savePost(newPost);
                            }
                    );
    }

    private void savePost(NewPost newPost) {
        repository.savePost(newPost)
                .subscribe(
                        post -> getView().insertPost(post),
                        throwable -> getView().showMessage(throwable.getMessage())
                );
    }
}
