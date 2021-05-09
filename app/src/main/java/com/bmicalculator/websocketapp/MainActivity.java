package com.bmicalculator.websocketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bmicalculator.websocketapp.adapter.AirQualityAdapter;
import com.bmicalculator.websocketapp.model.AirQuality;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WebSocketClient mWebSocketClient;
    private RecyclerView air_recyclerview;
    private Activity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        // Ui initialization
        init();

        // wenSocket method call
        connectWebSocket();


    }

    private void init() {

        air_recyclerview = findViewById(R.id.air_recyclerview);


    }

    private void connectWebSocket() {

        // webSocket Connection code here

        URI uri;
        try {
            uri = new URI("ws://city-ws.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket ", "Opened");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        // Ui update code

                        Log.d("Websocket ", message);
                        List<AirQuality> qualityList = new ArrayList<>();

                        try {
                            JSONArray jsonArray = new JSONArray(message);

                            //qualityList.removeAll(qualityList);


                            if (jsonArray.length() > 0) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    AirQuality quality = new Gson().fromJson(jsonArray.get(i).toString(), AirQuality.class);
                                    qualityList.add(quality);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.d("Websocket  ", qualityList.toString());

                        AirQualityAdapter qualityAdapter = new AirQualityAdapter(context, qualityList);
                        air_recyclerview.setAdapter(qualityAdapter);
                        //  qualityAdapter.add(qualityList);

                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
