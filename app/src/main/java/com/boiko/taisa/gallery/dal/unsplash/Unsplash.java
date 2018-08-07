package com.boiko.taisa.gallery.dal.unsplash;

import android.util.Log;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.entity.ImageResponseEntity;
import com.boiko.taisa.gallery.domain.entity.UserResponseEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Unsplash {
    private static Unsplash instance;
    private static final String URL = "https://api.unsplash.com/";
    private static String TAG = "API";
    private Retrofit retrofit;
    private UnsplashApiService api;

    public static synchronized Unsplash getInstance() {
        if (instance == null) {
            instance = new Unsplash();
        }
        return instance;
    }

    private Unsplash() {
        initApi();
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
    }

    public GalleryItem getImage() throws IOException {
        JsonElement response = api.getRandomImage().execute().body();
        GalleryItemBuilder builder = new GalleryItemBuilder(response);
        GalleryItem image = builder.build();
        //TODO: remove log
        Log.d(TAG, response.toString());
        return image;
    }

    private void initApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(UnsplashApiService.class);
    }

    private class GalleryItemBuilder {
        private JsonElement source;

        public GalleryItemBuilder(JsonElement source) {
            this.source = source;
        }

        private ImageResponseEntity buildImageSource() {
            JsonElement images = source.getAsJsonObject().get("urls");

            return new Gson().fromJson(images, ImageResponseEntity.class);
        }

        private UserResponseEntity buildUser() {
            JsonElement user = source.getAsJsonObject().get("user");

            return new Gson().fromJson(user, UserResponseEntity.class);
        }

        public GalleryItem build() {
            return new GalleryItem(buildImageSource().getRegular(), buildUser().getName());
        }
    }
}
