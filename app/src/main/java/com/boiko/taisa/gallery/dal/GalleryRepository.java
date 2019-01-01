package com.boiko.taisa.gallery.dal;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.util.List;

import io.reactivex.Observable;

public interface GalleryRepository {
    Observable<List<GalleryItem>> getRandomImageCollection(int size);
    Observable<List<GalleryItem>> searchImageCollection(String query, int size);
}
