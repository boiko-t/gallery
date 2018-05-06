package com.boiko.taisa.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.boiko.taisa.gallery.Models.GalleryItem;
import com.boiko.taisa.gallery.Models.JsonAssetsReader;
import com.squareup.picasso.Picasso;

public class GalleryActivity extends AppCompatActivity {

    private static final String assetsFileName = "galleryItems.json";
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initializeViews();
        loadPrimitiveData();
    }

    private void loadPrimitiveData() {
        JsonAssetsReader reader = new JsonAssetsReader(assetsFileName, this);
        GalleryItem[] collection = (GalleryItem[]) reader.getAssetsCollection();
        Picasso.with(this).load(collection[0].getUrl()).into(image);
        text.setText(collection[0].getDescription());
    }

    private void initializeViews() {
        image = findViewById(R.id.testImage);
        text = findViewById(R.id.testImageCaption);
    }
}
