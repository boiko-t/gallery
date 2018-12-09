package com.boiko.taisa.gallery.ui.mvp;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.util.List;

import io.reactivex.Observable;

public interface Gallery {
    interface Model {
        void loadData(int dataSize);
        void saveState();
        boolean isStateSaved();

        Observable<State> getStateObservable();

        Observable<Throwable> getErrorObservable();

        class State {
            public final List<GalleryItem> data;
            public final boolean isInProgress;

            State(List<GalleryItem> data, boolean isInProgress) {
                this.data = data;
                this.isInProgress = isInProgress;
            }
        }
    }

    interface View {
        void initRecyclerView(List<GalleryItem> data);
    }

    interface Presenter {
        void saveState();
        void onViewAttach(View view);
        void onViewDetach();
    }
}
