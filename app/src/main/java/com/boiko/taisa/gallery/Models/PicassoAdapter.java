package com.boiko.taisa.gallery.Models;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoAdapter {
    public static void setImageSrc(ImageView image, String src) {
        Picasso.get().load(src).into(image);
    }
}
