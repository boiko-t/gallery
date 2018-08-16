package com.boiko.taisa.gallery.domain.model;

import com.boiko.taisa.gallery.dal.AssetsFileLoader;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class GalleryModel {
    private static final String ASSETS_FILE_NAME = "galleryItems.json";

    private static GalleryModel instance;
    private AssetsFileLoader loader;

    private GalleryModel() {
        loader = new AssetsFileLoader(ASSETS_FILE_NAME);
    }

    public static synchronized GalleryModel getInstance() {
        if (instance == null) {
            instance = new GalleryModel();
        }
        return instance;
    }

    public List<GalleryItem> getGalleryData() {
        return getDataFromAsset();
    }

    private List<GalleryItem> getDataFromAsset() {
        try {
            Reader file = loader.getAssetsFile();
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<GalleryItem>>(){}.getType();
            return (List<GalleryItem>) gson.fromJson(file, collectionType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
