package com.boiko.taisa.gallery.domain.presenter;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.model.GalleryModel;
import com.boiko.taisa.gallery.ui.activity.GalleryActivity;

import java.util.List;

public class GalleryPresenter extends BasePresenter<GalleryActivity> {
    private GalleryModel model;

    public GalleryPresenter() {
        model = GalleryModel.getInstance();
    }

    @Override
    public void attachView(GalleryActivity view) {
        this.view = view;
        List<GalleryItem> data = model.getGalleryData();
        view.initRecyclerView(data);
    }
}
