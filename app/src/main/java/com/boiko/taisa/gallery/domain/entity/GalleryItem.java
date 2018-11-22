package com.boiko.taisa.gallery.domain.entity;

import com.boiko.taisa.gallery.BuildConfig;

/**
 * Created by taisa on 02.05.18.
 */

public class GalleryItem {
    private static final String AUTHOR_DESCRIPTION = BuildConfig.defaultAuthorDescription;
    private String url;
    private String description;
    private String author;

    public GalleryItem(String url) {
        this.url = url;
        this.description = "";
        this.author = "...";
    }

    public GalleryItem(String url, String author) {
        this.url = url;
        this.author = author;
    }

    public GalleryItem(String url, String description, String author) {
        this.url = url;
        this.description = description;
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return (description == null) ?
                AUTHOR_DESCRIPTION.concat(author)
                : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
