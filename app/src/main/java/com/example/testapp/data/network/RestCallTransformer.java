package com.example.testapp.data.network;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import retrofit2.Response;

public class RestCallTransformer<R> implements ObservableTransformer<Response<R>, R> {

    @Override
    public ObservableSource<R> apply(Observable<Response<R>> responce) {
        return NetworkStatusChecker.isInternetAvailable()
                .flatMap(aBoolean -> aBoolean ? responce : Observable.error(new Throwable("Network not available")))
                .flatMap(rResponse -> {
                    switch (rResponse.code()) {
                        case 200:
                        case 201:
                            return Observable.just(rResponse.body());
                        default:
                            return Observable.error(new Throwable("Request error"));
                    }
                });
    }
}
