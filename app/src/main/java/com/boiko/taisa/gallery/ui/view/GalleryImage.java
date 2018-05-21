package com.boiko.taisa.gallery.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.boiko.taisa.gallery.R;

public class GalleryImage extends View {
    private String caption;
    private boolean showCaption;
    private int captionPosition;

    public GalleryImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GalleryImage, 0, 0);
        try {
            caption = attributes.getString(R.styleable.GalleryImage_caption);
            showCaption = attributes.getBoolean(R.styleable.GalleryImage_showCaption, false);
            captionPosition = attributes.getInteger(R.styleable.GalleryImage_captionPosition, 0);
        } finally {
            attributes.recycle();
        }
    }
}