package com.boiko.taisa.gallery.domain.model;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoAdapter implements GalleryImageAdapter {

    @Override
    public void setImageSourceIntoView(ImageView imageView, String source) {
        Picasso.get().load(source).into(imageView);
    }
}
