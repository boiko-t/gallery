package com.boiko.taisa.gallery.dal.unsplash;

import android.util.Log;

import com.boiko.taisa.gallery.dal.GalleryItemBuilder;
import com.boiko.taisa.gallery.dal.GalleryRepository;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.entity.ImageResponseEntity;
import com.boiko.taisa.gallery.domain.entity.UserResponseEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
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
        List<GalleryItem> resultList = new ArrayList<>();
        api.getRandomCollection(10).enqueue(new Callback<List<JsonElement>>() {
            @Override
            public void onResponse(Call<List<JsonElement>> call, Response<List<JsonElement>> response) {
                GalleryItemBuilder builder;
                for (JsonElement json : response.body()) {
                    builder = new GalleryItemBuilder(json);
                    resultList.add(builder.build());
                }
            }

            @Override
            public void onFailure(Call<List<JsonElement>> call, Throwable t) {
                Log.d("galleryLog", "onFailure: ");
            }
        });
        return Observable.just(resultList);
    }
}
