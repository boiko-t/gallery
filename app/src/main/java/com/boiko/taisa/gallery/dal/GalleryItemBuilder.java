package com.boiko.taisa.gallery.dal;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.entity.ImageResponseEntity;
import com.boiko.taisa.gallery.domain.entity.UserResponseEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class GalleryItemBuilder {
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
