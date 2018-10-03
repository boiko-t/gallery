package com.boiko.taisa.gallery.dal;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.util.List;

public interface GalleryRepository {
    List<GalleryItem> getRandomCollection();
}
