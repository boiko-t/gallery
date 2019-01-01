package com.boiko.taisa.gallery.ui.mvp;

import android.util.Log;

import io.reactivex.disposables.Disposable;

public class GalleryPresenter implements Gallery.Presenter {

    private Gallery.Model model;
    private Disposable disposable;

    public GalleryPresenter(Gallery.Model model) {
        this.model = model;
    }

    @Override
    public void search(String query, Gallery.View view) {
        model.searchData(query);
//        model.getStateObservable()
//                .subscribe(state -> {
////                    if (!state.data.isEmpty()) view.updateData(state.data);
//                    Log.d("taisa", "search: " + state.data.get(0).getAuthor());
//                });
    }

    @Override
    public void saveState() {
        model.saveState();
    }

    @Override
    public void onViewAttach(Gallery.View view) {
        disposable = model
                .getStateObservable()
                .subscribe(state -> {
                    if (!state.data.isEmpty()) view.initRecyclerView(state.data);
                });
        if(!model.isStateSaved()) {
            model.loadRandomData(50);
        }
    }

    @Override
    public void onViewDetach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
