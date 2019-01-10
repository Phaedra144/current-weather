package com.weather.szilvi.currentweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText currentCity;
    TextView mainText;
    TextView tempText;
    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentCity = findViewById(R.id.enterCityEditText);
        mainText = findViewById(R.id.mainWeatherText);
        tempText = findViewById(R.id.tempTextView);
        background = findViewById(R.id.backgroundImage);
    }

    public void getWeather(View view) throws ExecutionException, InterruptedException {
        String enteredCity = currentCity.getText().toString();

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(currentCity.getWindowToken(), 0);

        DownloadTask task = new DownloadTask(mainText, tempText, background, getApplicationContext());
        task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + enteredCity + "&APPID=" +
                getString(R.string.api_key)
        ).get();
    }
}
