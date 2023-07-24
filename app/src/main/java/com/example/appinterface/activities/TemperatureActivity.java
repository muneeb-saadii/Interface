package com.example.appinterface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appinterface.R;

public class TemperatureActivity extends AppCompatActivity {

    private ProgressBar tempProgressBar, humidProgressBar;
    private TextView tempProgressText, humidProgressText;
    private int ROOM_TEMP = 38;
    private int ROOM_HUMIDITY = 65;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        tempProgressBar = findViewById(R.id.temp_progress_bar);
        tempProgressText = findViewById(R.id.temp_progress_text);
        humidProgressBar = findViewById(R.id.humid_progress_bar);
        humidProgressText = findViewById(R.id.humid_progress_text);

        set_temp_humidity();

    }

    private void set_temp_humidity() {

        tempProgressText.setText("" + ROOM_TEMP + "ºC");
        tempProgressBar.setProgress(ROOM_TEMP);

        humidProgressText.setText("" + ROOM_HUMIDITY + "%");
        humidProgressBar.setProgress(ROOM_HUMIDITY);
    }
}