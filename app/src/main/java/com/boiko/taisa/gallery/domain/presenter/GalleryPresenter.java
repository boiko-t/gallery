package com.boiko.taisa.gallery.domain.presenter;

import com.boiko.taisa.gallery.domain.model.GalleryModel;
import com.boiko.taisa.gallery.ui.activity.GalleryActivity;

public class GalleryPresenter extends BasePresenter<GalleryActivity, GalleryModel> {

    public GalleryPresenter(GalleryModel model) {
        super(model);
    }

    @Override
    public void onViewAttach(GalleryActivity view) {
        this.view = view;
        model.setListener(this);
        model.getData();
    }

    @Override
    public void onWorkComplete(GalleryModel.State state) {
        view.initRecyclerView(state.data);
    }
}
