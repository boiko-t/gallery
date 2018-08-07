package com.boiko.taisa.gallery.domain.entity;

public class ImageResponseEntity {
    private String regular;

    public ImageResponseEntity(String regular) {
        this.regular = regular;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}