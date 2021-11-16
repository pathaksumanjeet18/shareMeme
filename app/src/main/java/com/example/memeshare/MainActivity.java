package com.example.memeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        imageView = findViewById(R.id.imageView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMeme();
    }
    private void loadMeme(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://meme-api.herokuapp.com/gimme";

        // Request a string response from the provided URL.
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String url = response.optString("url");

                        Glide.with(getApplicationContext()).load(url).into(imageView);

                    }

                }, 
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public void shareMeme(View view) {

    }

    public void nextMeme(View view) {
        loadMeme();
    }
}