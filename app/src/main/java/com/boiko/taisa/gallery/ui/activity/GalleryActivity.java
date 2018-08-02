package com.boiko.taisa.gallery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boiko.taisa.gallery.R;
import com.boiko.taisa.gallery.dal.JsonAssetsReader;
import com.boiko.taisa.gallery.dal.Unsplash;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.ui.recyclerview.RecyclerViewAdapter;

public class GalleryActivity extends AppCompatActivity {

    private static final String ASSETS_FILE_NAME = "galleryItems.json";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        findViews();
        initRecyclerView();
        Unsplash unsplash = new Unsplash();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        GalleryItem[] collection = getCollectionData();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(collection);
        recyclerView.setAdapter(adapter);
    }

    private GalleryItem[] getCollectionData() {
        JsonAssetsReader<GalleryItem> reader = new JsonAssetsReader<>(ASSETS_FILE_NAME, this);
        return reader.getAssetsCollection(GalleryItem.class);
    }
}