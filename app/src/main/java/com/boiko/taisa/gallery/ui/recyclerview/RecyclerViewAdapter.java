package com.boiko.taisa.gallery.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;
import com.boiko.taisa.gallery.domain.model.GalleryImageAdapter;
import com.boiko.taisa.gallery.domain.model.PicassoAdapter;
import com.boiko.taisa.gallery.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private GalleryItem[] dataset;
    private final GalleryImageAdapter imageViewAdapter = new PicassoAdapter();

    public RecyclerViewAdapter(GalleryItem[] dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("onBindViewHolder", Integer.toString(position));
        holder.caption.setText(dataset[position].getDescription());
        imageViewAdapter.setImageSourceIntoView(holder.image, dataset[position].getUrl());
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;
        public ImageView image;
        public TextView caption;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.caption = itemView.findViewById(R.id.caption);
        }
    }
}
