package com.example.appinterface.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appinterface.MainActivity;
import com.example.appinterface.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout tempBtn;
    TextView weatherDegree, weatherDesc;
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // binding views
        weatherIcon = findViewById(R.id.weather_icon);
        weatherDegree = findViewById(R.id.weather_degree);
        weatherDesc = findViewById(R.id.weather_desc);
        tempBtn = findViewById(R.id.temp_sc);

        // click listeners
        tempBtn.setOnClickListener(new onClickList());

        //
        getWeatherData();
    }

    private void getWeatherData() {

//    https://api.openweathermap.org/data/2.5/weather?q=rawalpindi&appid=7787dcf9ad85f0b42c05890322de4e56
         final String url = "https://api.openweathermap.org/data/2.5/weather";
         final String api = "7787dcf9ad85f0b42c05890322de4e56";
         String city = "rawalpindi";


        final String hitURL = url + "?q=" + city + "&appid=" + api;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, hitURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject object = response.getJSONObject("main");
                    String temperature = object.getString("temp");
                    temperature = temperature.substring(0,3);
                    int temp = Integer.parseInt(temperature);
                    temp -= 273;
                    weatherDegree.setText("" + temp + "°C");

                    JSONArray array = response.getJSONArray("weather");
                    JSONObject obj = array.getJSONObject(0);
                    String desc = obj.getString("description");
                    weatherDesc.setText(desc.substring(0, 1).toUpperCase() + desc.substring(1));

                    desc = obj.getString("icon");
                    String url = "https://openweathermap.org/img/w/" + desc + ".png";

                    Picasso.get().load(url).resize(35,35).into(weatherIcon);
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

    private class onClickList implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int viewID = view.getId();

            if(viewID == R.id.temp_sc){
                Intent next = new Intent(getApplicationContext(), TemperatureActivity.class);
                startActivity(next);
            }
        }
    }

}