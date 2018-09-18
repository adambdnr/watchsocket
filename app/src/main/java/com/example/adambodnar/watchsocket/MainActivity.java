package com.example.adambodnar.watchsocket;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

public class MainActivity extends WearableActivity {

    private Button connectButton;
    private Button sendButton;
    boolean mBounded;
    ConnectionService connectionService;
    final private static String STARTFOREGROUND_ACTION = "com.example.adambodnar.watchsocket.action.startforeground";

    private View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("CONNECT");
            startService();
        }
    };

    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("SEND");
            connectionService.sendMessage("asd");
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

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, ConnectionService.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service is disconnected", 1000).show();
            mBounded = false;
            connectionService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service is connected", 1000).show();
            mBounded = true;
            ConnectionService.LocalBinder mLocalBinder = (ConnectionService.LocalBinder)service;
            connectionService = mLocalBinder.getServerInstance();
        }
    };


    private void startService() {
        System.out.println("STARTING SERVICE FROM MAIN");
        Intent serviceIntent = new Intent(MainActivity.this, ConnectionService.class);
        serviceIntent.setAction(STARTFOREGROUND_ACTION);
        startService(serviceIntent);
    }
}
