package com.boiko.taisa.gallery.dal.unsplash;


import com.boiko.taisa.gallery.BuildConfig;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UnsplashApiService {

    @Headers("Authorization: Client-ID " + BuildConfig.unsplashApiKey)
    @GET("photos/random")
    Call<JsonElement> getRandomImage();

    @Headers("Authorization: Client-ID " + BuildConfig.unsplashApiKey)
    @GET("photos/random")
    Call<List<JsonElement>> getRandomCollection(@Query("count") int count);
}
