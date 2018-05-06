package com.boiko.taisa.gallery.Models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

public class JsonAssetsReader<T> {
    private String fileName;
    private Context context;

    public JsonAssetsReader(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public T[] getAssetsCollection() {
        Reader file;
        try {
            file = getAssetsFile();
        } catch (IOException ex) {
            return null;
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<T[]>() {
        }.getType();
        return gson.fromJson(file, collectionType);
    }

    private InputStreamReader getAssetsFile() throws IOException {
        InputStreamReader stream = new InputStreamReader(context.getAssets().open(fileName));
        return stream;
    }
}
