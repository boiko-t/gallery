package com.boiko.taisa.gallery.domain.presenter;

import com.boiko.taisa.gallery.domain.model.BaseModel;
import com.boiko.taisa.gallery.domain.model.GalleryModel;
import com.boiko.taisa.gallery.domain.view.BaseView;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements BaseModel.Listener {
    protected V view;
    protected M model;

    BasePresenter(M model) {
        this.model = model;
    }

    public abstract void onViewAttach(V view);

    public void onViewDetach() {
        model.removeListener();
        view = null;
    }
}
