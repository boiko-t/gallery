package com.boiko.taisa.gallery.dal;

import android.os.AsyncTask;
import android.util.Log;

import com.boiko.taisa.gallery.BuildConfig;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Unsplash implements ApiRequestSender {
    private static final String URL = "https://api.unsplash.com/photos/random/";
    private static final String AUTH_HEADER_NAME = "Authorization:";
    private static final String AUTH_HEADER_PREFIX = "Client-ID ";
    private static String TAG = "API";

    public Unsplash() {
        Observable.fromCallable(() -> getImage())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<GalleryItem>() {
                    @Override
                    public void onNext(GalleryItem galleryItem) {
                        Log.d(TAG, "onNext:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
//        Log.d(TAG, getImage().getDescription());
    }

    @Override
    public HttpURLConnection getApiConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String key = BuildConfig.unsplashApiKey;
        connection.setRequestProperty(AUTH_HEADER_NAME, AUTH_HEADER_PREFIX.concat(key));
        return connection;
    }

    @Override
    public GalleryItem getImage() {
        try {
            URL url = new URL(URL);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getApiConnection(url)
                            .getInputStream()));
            Gson gson = new Gson();
            Log.d("API", reader.readLine());
            GalleryItem test = gson.fromJson(reader, GalleryItem.class);
            return test;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GalleryItem();
    }
}
