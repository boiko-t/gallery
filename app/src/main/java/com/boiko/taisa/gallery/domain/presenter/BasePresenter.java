package com.boiko.taisa.gallery.domain.presenter;

import com.boiko.taisa.gallery.domain.view.BaseView;

public abstract class BasePresenter<T extends BaseView> {
    protected T view;

    public abstract void attachView(T view);

    public void detachView() {
        view = null;
    }
}
