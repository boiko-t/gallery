package com.boiko.taisa.gallery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boiko.taisa.gallery.R;
import com.boiko.taisa.gallery.domain.adapter.image.PicassoGalleryImageAdapter;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.ui.mvp.Gallery;
import com.boiko.taisa.gallery.ui.mvp.GalleryModel;
import com.boiko.taisa.gallery.ui.mvp.GalleryPresenter;
import com.boiko.taisa.gallery.ui.recyclerview.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements Gallery.View {

    private final GalleryModel model = GalleryModel.getInstance();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Gallery.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        findViews();
        presenter = new GalleryPresenter(model);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttach(this);
    }

    @Override
    protected void onStop() {
        presenter.onViewDetach();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter = null;
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.rvGallery);
    }

    @Override
    public void initRecyclerView(List<GalleryItem> data) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(data, new PicassoGalleryImageAdapter(Picasso.get()));
        recyclerView.setAdapter(adapter);
    }
}