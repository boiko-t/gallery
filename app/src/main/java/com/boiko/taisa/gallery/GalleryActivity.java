package com.boiko.taisa.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.boiko.taisa.gallery.Models.GalleryItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        InputStreamReader stream;
        try {
            stream = new InputStreamReader(getAssets().open("galleryItems.json"));
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        GalleryItem item = gson.fromJson(stream, GalleryItem.class);
    }
}
