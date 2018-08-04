package com.boiko.taisa.gallery.dal;

import com.boiko.taisa.gallery.domain.entity.GalleryItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface UnsplashApiService {
    HttpURLConnection getApiConnection(URL url) throws IOException;
    GalleryItem getImage();
}
