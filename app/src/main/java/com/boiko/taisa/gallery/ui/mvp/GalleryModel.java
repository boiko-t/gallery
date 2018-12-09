package com.boiko.taisa.gallery.ui.mvp;

import com.boiko.taisa.gallery.dal.GalleryRepository;
import com.boiko.taisa.gallery.dal.unsplash.GalleryUnsplashRepository;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class GalleryModel implements Gallery.Model {
    private static GalleryModel INSTANCE;
    private boolean isStateSaved = false;
    private GalleryRepository repository;
    private BehaviorSubject<State> state = BehaviorSubject.create();
    private PublishSubject<Throwable> errorState = PublishSubject.create();

    private GalleryModel() {
        this.repository = new GalleryUnsplashRepository();
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
    public void loadData(int dataSize) {
        state.onNext(new State(new ArrayList<>(), true));
        repository.getRandomImageCollection(dataSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->
                    GalleryModel.this.state.onNext(new State(data, false))
                );
    }

    @Override
    public void saveState() {
        isStateSaved = true;
    }

    @Override
    public boolean isStateSaved() {
        return isStateSaved;
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
