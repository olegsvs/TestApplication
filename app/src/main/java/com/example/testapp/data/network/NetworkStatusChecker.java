package com.example.testapp.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.testapp.App;

import io.reactivex.Observable;

public class NetworkStatusChecker {
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    static Observable<Boolean> isInternetAvailable() {
        return Observable.just(NetworkStatusChecker.isNetworkAvailable());
    }
}
