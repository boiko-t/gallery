package com.boiko.taisa.gallery.dal.unsplash;

import android.support.annotation.Nullable;
import android.util.Log;

import com.boiko.taisa.gallery.dal.GalleryItemBuilder;
import com.boiko.taisa.gallery.dal.GalleryRepository;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.entity.ImageResponseEntity;
import com.boiko.taisa.gallery.domain.entity.UserResponseEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryUnsplashRepository implements GalleryRepository {
    private static final String URL = "https://api.unsplash.com/";
    private Retrofit retrofit;
    private UnsplashApiService api;

    public GalleryUnsplashRepository() {
        initApi();
    }

    private void initApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(UnsplashApiService.class);
    }

    public GalleryItem getRandomImage() {
        try {
            JsonElement response = api.getRandomImage().execute().body();
            GalleryItemBuilder builder = new GalleryItemBuilder(response);
            GalleryItem image = builder.build();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Observable<List<GalleryItem>> getRandomCollection() {
        return Observable.create(emitter -> {
            List<GalleryItem> resultList = new ArrayList<>();
            try {
                List<JsonElement> response = api.getRandomCollection(10).execute().body();
                for (JsonElement json : response) {
                    GalleryItemBuilder builder = new GalleryItemBuilder(json);
                    resultList.add(builder.build());
                }
                emitter.onNext(resultList);
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }
}
