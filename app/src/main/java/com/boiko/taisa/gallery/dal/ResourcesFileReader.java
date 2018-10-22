package com.boiko.taisa.gallery.dal;

import android.content.Context;

import com.boiko.taisa.gallery.GalleryApplication;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {
    private static final String RAW_EXTENSION = "raw";
    private final String fileName;
    private Context context;

    public ResourcesFileReader(String fileName) {
        this.fileName = fileName;
        this.context = GalleryApplication.getContext();
    }

    public InputStreamReader getRawFile() {
        int id = context.getResources().getIdentifier(fileName, RAW_EXTENSION, context.getPackageName());
        InputStream stream = context.getResources().openRawResource(id);
        return new InputStreamReader(stream);
    }
}
