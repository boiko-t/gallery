package com.boiko.taisa.gallery.dal.unsplash;

import com.boiko.taisa.gallery.dal.GalleryItemBuilder;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GalleryItemDeserializer implements JsonDeserializer<GalleryItem> {

    @Override
    public GalleryItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new GalleryItemBuilder(json).build();
    }
}