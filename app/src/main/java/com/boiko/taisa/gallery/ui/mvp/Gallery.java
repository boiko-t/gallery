package com.boiko.taisa.gallery.ui.mvp;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.util.List;

public interface Gallery {
    interface Model {
        void loadData();

        void addListener(Listener listener);

        void removeListener(Listener listener);

        interface Listener {
            void onWorkComplete(State state);
        }

        class State {
            public final List<GalleryItem> data;

            State(List<GalleryItem> data) {
                this.data = data;
            }
        }
    }

    interface View {
        void initRecyclerView(List<GalleryItem> data);
    }

    interface Presenter {
        void onViewAttach(View view);

        void onViewDetach();
    }
}
