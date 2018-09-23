package com.boiko.taisa.gallery.domain.model;

import com.boiko.taisa.gallery.BuildConfig;
import com.boiko.taisa.gallery.dal.ResourcesFileLoader;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class GalleryModel implements BaseModel {
    private static final String DATA_FILE_NAME = BuildConfig.defaultGalleryData;

    private static GalleryModel INSTANCE;
    private ResourcesFileLoader loader;
    private Listener listener;
    private State state;

    private GalleryModel() {
        loader = new ResourcesFileLoader(DATA_FILE_NAME);
    }

    public static synchronized GalleryModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GalleryModel();
        }
        return INSTANCE;
    }

    private List<GalleryItem> getDataFromAsset() {
        Reader file = loader.getRawFile();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GalleryItem>>() {
        }.getType();
        return (List<GalleryItem>) gson.fromJson(file, collectionType);
    }

    @Override
    public void getData() {
        state = new State(getDataFromAsset());
        listener.onWorkComplete(state);
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
        if (state != null) {
            listener.onWorkComplete(state);
        }
    }

    @Override
    public void removeListener() {
        this.listener = null;
    }

    public class State implements ModelState {
        public final List<GalleryItem> data;

        public State(List<GalleryItem> data) {
            this.data = data;
        }
    }
}
