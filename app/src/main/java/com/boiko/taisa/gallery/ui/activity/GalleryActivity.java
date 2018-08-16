package com.boiko.taisa.gallery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boiko.taisa.gallery.R;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.presenter.BasePresenter;
import com.boiko.taisa.gallery.domain.presenter.GalleryPresenter;
import com.boiko.taisa.gallery.domain.view.BaseView;
import com.boiko.taisa.gallery.ui.recyclerview.RecyclerViewAdapter;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements BaseView {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BasePresenter<GalleryActivity> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        findViews();
        createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    public void initRecyclerView(List<GalleryItem> data) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void createPresenter() {
        presenter = new GalleryPresenter();
        presenter.attachView(this);
    }
}