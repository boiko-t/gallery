package com.boiko.taisa.gallery.dal;

import com.boiko.taisa.gallery.BuildConfig;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;

public class GalleryLocalRepository implements GalleryRepository {
    private static final String DATA_FILE_NAME = BuildConfig.defaultGalleryData;

    @Override
    public Observable<List<GalleryItem>> getRandomCollection() {
        ResourcesFileReader reader = new ResourcesFileReader(DATA_FILE_NAME);
        Reader file = reader.getRawFile();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GalleryItem>>() {
        }.getType();
        return Observable.just((List<GalleryItem>) gson.fromJson(file, collectionType));
    }
}
