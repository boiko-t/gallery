package com.boiko.taisa.gallery.ui.mvp;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GalleryPresenter implements Gallery.Presenter {

    private Gallery.Model model;
    private Disposable disposable;

    public GalleryPresenter(Gallery.Model model) {
        this.model = model;
    }

    @Override
    public void onViewAttach(Gallery.View view) {
        disposable = model
                .getStateObservable()
                .subscribe(state -> {
                    if (!state.data.isEmpty()) view.initRecyclerView(state.data);
                });
        if(!view.getViewRestoreState()) {
            model.loadData();
        }
    }

    @Override
    public void onViewDetach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
