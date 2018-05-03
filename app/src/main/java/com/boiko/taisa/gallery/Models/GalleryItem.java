package com.boiko.taisa.gallery.Models;

/**
 * Created by taisa on 02.05.18.
 */

public class GalleryItem {
    private String url;
    private String description;

    public GalleryItem() {
    }

    public GalleryItem(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
