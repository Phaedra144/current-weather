package com.weather.szilvi.currentweather;

import android.os.AsyncTask;
import android.util.Log;

import com.weather.szilvi.currentweather.models.WeatherResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadTask extends AsyncTask<String, Void, WeatherResponse> {

    @Override
    protected WeatherResponse doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            urlConnection.connect();

            ObjectInputStream ois = new ObjectInputStream(in);
            Log.i("Ois", ois.toString());
            WeatherResponse weatherResponse = (WeatherResponse) ois.readObject();
            Log.i("WeatherResp", weatherResponse.toString());
            return weatherResponse;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
