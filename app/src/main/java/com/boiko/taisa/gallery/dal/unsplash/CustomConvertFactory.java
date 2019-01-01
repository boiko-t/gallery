package com.boiko.taisa.gallery.dal.unsplash;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class CustomConvertFactory extends Converter.Factory {
    private final Gson gson;

    private CustomConvertFactory(Gson gson) {
        super();
        this.gson = gson;
    }

    public static CustomConvertFactory create(Gson gson) {
        return new CustomConvertFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter);
    }

    final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final String RESULTS_ARRAY = "results";
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override public T convert(ResponseBody value) {
            JsonElement json = gson.fromJson(value.charStream(), JsonElement.class);
            JsonElement jsonTree;
            if(json.isJsonObject()) {
                jsonTree = json.getAsJsonObject().getAsJsonArray(RESULTS_ARRAY);
            } else  {
                jsonTree = json;
            }
            value.close();
            return adapter.fromJsonTree(jsonTree);
        }
    }
}
