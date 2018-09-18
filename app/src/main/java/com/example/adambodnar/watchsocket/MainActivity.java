package com.example.adambodnar.watchsocket;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends WearableActivity {

    private Button connectButton;
    private Button sendButton;

    private View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("CONNECT");
        }
    };

    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("SEND");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectButton = (Button) findViewById(R.id.button_connect);
        connectButton.setOnClickListener(connectListener);
        sendButton = (Button) findViewById(R.id.button_send);
        sendButton.setOnClickListener(sendListener);

        // Enables Always-on
        setAmbientEnabled();
    }
}
