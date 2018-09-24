package com.boiko.taisa.gallery.ui.mvp;

public class GalleryPresenter implements Gallery.Presenter, Gallery.Model.Listener {

    private Gallery.View view;
    private Gallery.Model model;

    public GalleryPresenter(Gallery.Model model) {
        this.model = model;
    }

    @Override
    public void onViewAttach(Gallery.View view) {
        this.view = view;
        model.addListener(this);
        model.loadData();
    }

    @Override
    public void onViewDetach() {
        this.view = null;
        this.model.removeListener(this);
    }

    @Override
    public void onWorkComplete(Gallery.Model.State state) {
        view.initRecyclerView(state.data);
    }
}
