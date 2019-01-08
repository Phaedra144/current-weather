package com.weather.szilvi.currentweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.weather.szilvi.currentweather.models.WeatherResponse;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentCity = findViewById(R.id.enterCityEditText);
    }

    public void getWeather(View view) throws ExecutionException, InterruptedException {
        String enteredCity = currentCity.getText().toString();
        DownloadTask task = new DownloadTask();
        WeatherResponse weatherResponse = task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + enteredCity + "&APPID=" + System.getenv("APIKEY")).get();
    }
}
