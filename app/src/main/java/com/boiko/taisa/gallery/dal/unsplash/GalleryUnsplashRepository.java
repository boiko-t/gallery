package com.boiko.taisa.gallery.dal.unsplash;

import com.boiko.taisa.gallery.dal.GalleryRepository;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GalleryItem.class, new UnsplashResponseDeserializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(CustomConvertFactory.create(gson))
                .build();
        api = retrofit.create(UnsplashApiService.class);
    }

    @Override
    public Observable<List<GalleryItem>> getRandomImageCollection(int size) {
        return Observable.create(emitter -> {
            try {
                List<GalleryItem> resultList =
                        api.getRandomImageCollection(size).execute().body();
                emitter.onNext(resultList);
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }

    @Override
    public Observable<List<GalleryItem>> searchImageCollection(String query, int size) {
        return Observable.create(emitter -> {
            try {
                List<GalleryItem> resultList = api.searchImageCollection(query)
                                .execute().body();
                emitter.onNext(resultList);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }
}
