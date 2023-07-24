package com.example.appinterface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appinterface.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TemperatureActivity extends AppCompatActivity {

    private ProgressBar tempProgressBar;
    private TextView tempProgressText, sensorTemp, sensorHumid;
    private TextView weatherFeelsLike, weatherStatus, weatherCity;
    private ImageView weatherIcon;
    private int ROOM_TEMP = 38;
    private int ROOM_HUMIDITY = 65;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        tempProgressBar = findViewById(R.id.temp_progress_bar);
        tempProgressText = findViewById(R.id.temp_progress_text);
        sensorTemp = findViewById(R.id.sensor_temp);
        sensorHumid = findViewById(R.id.sensor_humid);

        weatherFeelsLike = findViewById(R.id.weather_feels_degree);
        weatherStatus = findViewById(R.id.weather_status);
        weatherCity = findViewById(R.id.weather_city);
        weatherIcon = findViewById(R.id.weather_img);


        set_temp_humidity();
        getWeatherData();

    }

    private void set_temp_humidity() {

        tempProgressText.setText("" + ROOM_TEMP + "ºC");
        tempProgressBar.setProgress(ROOM_TEMP);

        sensorTemp.setText(""+ ROOM_TEMP + "°C");
        sensorHumid.setText(""+ ROOM_HUMIDITY + "%");
    }


    private void getWeatherData() {

//    https://api.openweathermap.org/data/2.5/weather?q=rawalpindi&appid=7787dcf9ad85f0b42c05890322de4e56
        final String url = "https://api.openweathermap.org/data/2.5/weather";
        final String api = "7787dcf9ad85f0b42c05890322de4e56";
        String city = "islamabad";


        final String hitURL = url + "?q=" + city + "&appid=" + api;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, hitURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject object = response.getJSONObject("main");
                    String feels_like = object.getString("feels_like");
                    feels_like = feels_like.substring(0,3);
                    int temp = Integer.parseInt(feels_like);
                    temp -= 273;    // kelvin to celcius
                    weatherFeelsLike.setText("" + temp + "°C");

                    JSONArray array = response.getJSONArray("weather");
                    JSONObject obj = array.getJSONObject(0);
                    String desc = obj.getString("description");
                    weatherStatus.setText(desc.substring(0, 1).toUpperCase() + desc.substring(1));

                    desc = obj.getString("icon");
                    String url = "https://openweathermap.org/img/w/" + desc + ".png";
                    Picasso.get().load(url).resize(35,30).into(weatherIcon);

                    String cityName = response.getString("name");
                    weatherCity.setText(cityName);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}