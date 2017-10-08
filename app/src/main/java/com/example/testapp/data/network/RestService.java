package com.example.testapp.data.network;

import com.example.testapp.data.models.Album;
import com.example.testapp.data.models.NewPost;
import com.example.testapp.data.models.Photo;
import com.example.testapp.data.models.Post;
import com.example.testapp.data.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestService {

    @GET("users")
    Observable<Response<List<User>>> getUsers();

    @GET("users/{user_id}")
    Observable<Response<User>> getUserById(@Path("user_id") int userId);

    @GET("users/{user_id}/albums")
    Observable<Response<List<Album>>> getUserAlbums(@Path("user_id") int userId);

    @GET("users/{user_id}/posts")
    Observable<Response<List<Post>>> getUserPosts(@Path("user_id") int userId);

    @GET("albums/{album_id}/photos")
    Observable<Response<List<Photo>>> getAlbumPhoto(@Path("album_id") int albumId);

    @POST("posts")
    Observable<Response<Post>> savePost(@Body NewPost newPost);
 }
