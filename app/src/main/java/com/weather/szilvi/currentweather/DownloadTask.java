package com.weather.szilvi.currentweather;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.szilvi.currentweather.models.WeatherResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {

    private TextView mainText;
    private TextView tempText;
    private ImageView background;
    private Context context;

    public DownloadTask(TextView mainText, TextView tempText, ImageView background, Context context) {
        this.mainText = mainText;
        this.tempText = tempText;
        this.background = background;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            convertJSONResult(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException nullE) {
            Toast.makeText(context, "Please enter correct city name", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertJSONResult(String jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weatherResponse = objectMapper.readValue(jsonObject, WeatherResponse.class);
        setUi(weatherResponse);
    }

    private void setUi(WeatherResponse weatherResponse) {
        int imgsrc = getMainInfoImageSrc(weatherResponse.getWeather().get(0).getMain());
        background.setImageResource(imgsrc);
        mainText.setText(weatherResponse.getWeather().get(0).getMain());
        double celsius = weatherResponse.getMain().getTemp() - 273.15;
        tempText.setText(String.format("%.0f", celsius) + "°");
    }

    private int getMainInfoImageSrc(String mainInfo) {
        int drawableSrc = 0;
        switch (mainInfo) {
            case "Clear":
                drawableSrc = R.drawable.sunshine;
                break;
            case "Clouds":
                drawableSrc = R.drawable.clouds;
                break;
            case "Rain":
                drawableSrc = R.drawable.rain;
                break;
            case "Fog":
                drawableSrc = R.drawable.fog;
                break;
            case "Snow":
                drawableSrc = R.drawable.snow;
                break;
            default:
                drawableSrc = R.drawable.clouds;
        }
        return drawableSrc;
    }
}
