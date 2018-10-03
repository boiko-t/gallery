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
                .subscribe(new Consumer<Gallery.Model.State>() {
                    @Override
                    public void accept(Gallery.Model.State state) throws Exception {
                       if(!state.data.isEmpty()) view.initRecyclerView(state.data);
                    }
                });
        model.loadData();
    }

    @Override
    public void onViewDetach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
