package com.boiko.taisa.gallery.domain.adapter.image;

import android.widget.ImageView;

public interface ImageLoader {
    void loadImage(ImageView imageView, String source);
}