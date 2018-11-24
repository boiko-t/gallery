package com.boiko.taisa.gallery.dal.unsplash;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UnsplashResponseDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String src = json.getAsJsonObject()
                .get("urls")
                .getAsJsonObject()
                .get("regular")
                .getAsString();
        String author = json.getAsJsonObject()
                .get("user")
                .getAsJsonObject()
                .get("name")
                .getAsString();
        return new GalleryItem(src, author);
    }
}