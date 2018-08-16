package com.boiko.taisa.gallery.dal;

import android.content.Context;

import com.boiko.taisa.gallery.ApplicationContext;

import java.io.IOException;
import java.io.InputStreamReader;

public class AssetsFileLoader {
    private String fileName;
    private Context context;

    public AssetsFileLoader(String fileName) {
        this.fileName = fileName;
        this.context = ApplicationContext.getContext();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStreamReader getAssetsFile() throws IOException {
        InputStreamReader stream = new InputStreamReader(context.getAssets().open(fileName));
        return stream;
    }
}
