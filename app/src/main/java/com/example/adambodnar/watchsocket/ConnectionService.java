package com.example.adambodnar.watchsocket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ConnectionService extends Service {

    IBinder mBinder = new LocalBinder();
    private ConnectionHelper connectionHelper = new ConnectionHelper();

    public class LocalBinder extends Binder {
        public ConnectionService getServerInstance() {
            return ConnectionService.this;
        }
    }

    //for implementing the connection
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("CREATING SERVICE");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("ON START COMMAND");
        connectionHelper.createWebSocket();
        connectionHelper.connectToWebSocket();
        return super.onStartCommand(intent, flags, startId);
    }

    //for closing the connection and necessary afterwork
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void sendMessage(String message) {
        if (connectionHelper.isWebSocketOpen()) {
            connectionHelper.sendMessage(message);
        }
    }
}
