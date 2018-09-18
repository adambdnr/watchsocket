package com.example.adambodnar.watchsocket;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class ConnectionHelper {

    private WebSocket ws;

    public ConnectionHelper() {
    }

    public void createWebSocket() {
        WebSocketFactory factory = new WebSocketFactory();
        try {
            Log.i("WS", "Creating websocket");
            ws = factory.createSocket("ws://178.128.199.220:8080");
            ws.addListener(new WebSocketAdapter() {
                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    // Received a text message.
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectToWebSocket() {
        ws.connectAsynchronously();
        Log.i("WS", "Connected to websocket");
    }
    public void sendMessage(String message) {
        Log.i("WS", "Sending message to socket");
        ws.sendText(message);
    }
    public boolean isWebSocketOpen() {
        return ws.isOpen();
    }
}
