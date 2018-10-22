package com.boiko.taisa.gallery.ui.mvp;

import android.util.Log;

import com.boiko.taisa.gallery.dal.GalleryLocalRepository;
import com.boiko.taisa.gallery.dal.GalleryRepository;
import com.boiko.taisa.gallery.dal.unsplash.GalleryUnsplashRepository;
import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class GalleryModel implements Gallery.Model {
    private static GalleryModel INSTANCE;
    private GalleryRepository repository;
    private BehaviorSubject<State> state = BehaviorSubject.create();
    private PublishSubject<Throwable> errorState = PublishSubject.create();

    private GalleryModel() {
        this.repository = new GalleryUnsplashRepository();
//        this.repository = new GalleryLocalRepository();
    }

    public static GalleryModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GalleryModel();
        }
        return INSTANCE;
    }

    public void setRepository(GalleryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadData() {
        state.onNext(new State(new ArrayList<>(), true));
        repository.getRandomCollection()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("galleryLog", "accept: " + data);
                    GalleryModel.this.state.onNext(new State(data, true));
                });
    }

    @Override
    public Observable<State> getStateObservable() {
        return state;
    }

    @Override
    public Observable<Throwable> getErrorObservable() {
        return errorState;
    }
}
