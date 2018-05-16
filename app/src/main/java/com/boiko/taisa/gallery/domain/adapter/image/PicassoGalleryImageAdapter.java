package com.boiko.taisa.gallery.domain.adapter.image;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoGalleryImageAdapter implements GalleryImageAdapter {

    @Override
    public void setImageSourceIntoView(ImageView imageView, String source) {
        Picasso.get().load(source).into(imageView);
    }
}
