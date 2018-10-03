package com.boiko.taisa.gallery.ui.mvp;

import android.util.Log;

import com.boiko.taisa.gallery.dal.GalleryLocalRepository;
import com.boiko.taisa.gallery.dal.GalleryRepository;

import java.util.ArrayList;
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
        this.repository = new GalleryLocalRepository();
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
        Observable.just(new State(repository.getRandomCollection(), false))
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<State>() {
                    @Override
                    public void accept(State state) throws Exception {
                        if (new Random().nextInt() % 3 == 0) {
                            Log.d(GalleryModel.class.getSimpleName(), "accept: " + state);
                            GalleryModel.this.state.onNext(state);
                        } else {
                            Log.d(GalleryModel.class.getSimpleName(), "simulated error: ");
                            GalleryModel.this.errorState.onNext(new Throwable("simulated error"));
                        }
                    }
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
