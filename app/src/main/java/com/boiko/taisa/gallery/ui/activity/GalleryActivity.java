package com.boiko.taisa.gallery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.boiko.taisa.gallery.R;
import com.boiko.taisa.gallery.domain.adapter.image.PicassoGalleryImageAdapter;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.ui.mvp.Gallery;
import com.boiko.taisa.gallery.ui.mvp.GalleryModel;
import com.boiko.taisa.gallery.ui.mvp.GalleryPresenter;
import com.boiko.taisa.gallery.ui.recyclerview.RecyclerViewAdapter;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class GalleryActivity extends AppCompatActivity implements Gallery.View {

    private static final int MIN_SEARCH_QUERY_SIZE = 3;
    private final GalleryModel model = GalleryModel.getInstance();
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Observable<String> searchQueryObservable;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Gallery.Presenter presenter;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        findViews();
        initObservable();
        initSearchSubscription();
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
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.rvGallery);
        searchView = findViewById(R.id.svMainSearch);
    }

    private void initObservable() {
        searchQueryObservable = RxSearchView
                .queryTextChanges(searchView)
                .map(CharSequence::toString);
    }

    private void initSearchSubscription() {
        disposable = searchQueryObservable
                .filter(this::validateSearchQuery)
                .distinctUntilChanged()
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(query -> presenter.search(query, this));
    }

    private boolean validateSearchQuery(String value) {
        return !value.isEmpty() && value.length() >= MIN_SEARCH_QUERY_SIZE;
    }

    @Override
    public void initRecyclerView(List<GalleryItem> data) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(data, new PicassoGalleryImageAdapter(Picasso.get()));
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateData(List<GalleryItem> data) {
        adapter.setDataSet(data);
        recyclerView.setAdapter(adapter);
    }
}