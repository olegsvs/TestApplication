package com.example.testapp.data;

import com.example.testapp.App;
import com.example.testapp.data.models.Album;
import com.example.testapp.data.models.NewPost;
import com.example.testapp.data.models.Photo;
import com.example.testapp.data.models.Post;
import com.example.testapp.data.models.User;
import com.example.testapp.di.components.DataComponent;
import com.example.testapp.data.network.RestService;
import com.example.testapp.data.network.RestCallTransformer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.POST;

public class Repository {
    @Inject
    RestService restService;

    public Repository() {
        initDaggerComponent();
    }

    private void initDaggerComponent() {
        DataComponent component = App.getComponent();
        if (component != null)
            component.inject(this);
    }

    public Observable<User> getUsers() {
        return restService.getUsers()
                .compose(new RestCallTransformer<>())
                .flatMap(users -> Observable.fromIterable(users))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> getUserById(int userId) {
        return restService.getUserById(userId)
                .compose(new RestCallTransformer<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Album> getUserAlbums(int userId) {
        return restService.getUserAlbums(userId)
                .compose(new RestCallTransformer<>())
                .flatMap(albums -> Observable.fromIterable(albums))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Post> getUserPosts(int userId) {
        return restService.getUserPosts(userId)
                .compose(new RestCallTransformer<>())
                .flatMap(posts -> Observable.fromIterable(posts))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Photo> getAlbumPhoto(int albumId) {
        return  restService.getAlbumPhoto(albumId)
                .compose(new RestCallTransformer<>())
                .flatMap(photos -> Observable.fromIterable(photos))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Post> savePost(NewPost newPost) {
        return restService.savePost(newPost)
                .compose(new RestCallTransformer<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
