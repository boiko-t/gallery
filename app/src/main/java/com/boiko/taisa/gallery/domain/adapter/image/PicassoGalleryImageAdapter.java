package com.boiko.taisa.gallery.domain.adapter.image;

import android.widget.ImageView;

import com.boiko.taisa.gallery.R;
import com.squareup.picasso.Picasso;

public class PicassoGalleryImageAdapter implements ImageLoader {

    private final Picasso picasso;

    public PicassoGalleryImageAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void loadImage(ImageView imageView, String source) {
        int placeholder = R.drawable.placeholder;
        picasso.load(source)
                .placeholder(placeholder)
                .into(imageView);
    }
}
