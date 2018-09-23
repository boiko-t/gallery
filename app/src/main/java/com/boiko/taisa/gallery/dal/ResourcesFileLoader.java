package com.boiko.taisa.gallery.dal;

import android.content.Context;

import com.boiko.taisa.gallery.Gallery;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileLoader {
    private static final String RAW_EXTENTION = "raw";
    private final String fileName;
    private Context context;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        this.context = Gallery.getContext();
    }

    public String getFileName() {
        return fileName;
    }

    public InputStreamReader getRawFile() {
        int id = context.getResources().getIdentifier(fileName, RAW_EXTENTION, context.getPackageName());
        InputStream stream = context.getResources().openRawResource(id);
        return new InputStreamReader(stream);
    }
}
