package com.boiko.taisa.gallery.dal;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Unsplash {
    private static final String URL = "https://api.unsplash.com/photos/random/";

    public Unsplash() {
        Test t = new Test();
        t.execute(URL);
    }

    private class Test extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            try {
                java.net.URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Authorization:", "Client-ID 840d21b0d5b76d54ecd48221b246ac3db08f513eedf8f77fc1a40efb4d363ee8");
                BufferedReader rd = new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
                Log.d("Gallery", rd.readLine());
            } catch (Exception e) {
                Log.d("Gallery", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
