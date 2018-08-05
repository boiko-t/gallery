package com.boiko.taisa.gallery.dal;

import android.util.Log;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Unsplash {
    private static final String URL = "https://api.unsplash.com/";
    private static final String AUTH_HEADER_NAME = "Authorization:";
    private static final String AUTH_HEADER_PREFIX = "Client-ID ";
    private static String TAG = "API";
    private Retrofit retrofit;
    private UnsplashApiService api;

    public Unsplash() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(UnsplashApiService.class);
        Observable.fromCallable(() -> getImage())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<GalleryItem>() {
                    @Override
                    public void onNext(GalleryItem galleryItem) {
//                        Log.d(TAG, "onNext:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
                    }
                });
//        Log.d(TAG, getImage().getDescription());
    }

    public GalleryItem getImage() {
        try {
            JsonElement response = api.getRandomImage().execute().body();
            int a = 5;
            Log.d(TAG, String.valueOf(a));
            Log.d(TAG, response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GalleryItem();
    }
}
