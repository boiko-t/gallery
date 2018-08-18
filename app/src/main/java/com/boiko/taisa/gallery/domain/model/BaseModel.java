package com.boiko.taisa.gallery.domain.model;

public interface BaseModel {
    void getData();

    void setListener(Listener listener);

    void removeListener();

    interface Listener {
        void onWorkComplete(GalleryModel.State state);
    }

    interface ModelState {
    }
}