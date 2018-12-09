package com.boiko.taisa.gallery.dal.unsplash;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UnsplashResponseDeserializer implements JsonDeserializer {
    private final String IMAGE_OBJECT = "urls";
    private final String IMAGE_SOURCE = "regular";
    private final String USER_OBJECT = "user";
    private final String USER_NAME = "name";

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String src = json.getAsJsonObject()
                .get(IMAGE_OBJECT).getAsJsonObject()
                .get(IMAGE_SOURCE).getAsString();
        String author = json.getAsJsonObject()
                .get(USER_OBJECT).getAsJsonObject()
                .get(USER_NAME).getAsString();
        return new GalleryItem(src, author);
    }
}