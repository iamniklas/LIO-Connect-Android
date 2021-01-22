package de.niklasenglmeier.lioconnect.network;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    Socket socket;
    public Sender sender;
    public Receiver receiver;

    NetworkCallbacks callbacks;

    private final String ip;
    private final int port;

    public Client(NetworkCallbacks _callbackObject, String _ip, int _port) {
        callbacks = _callbackObject;
        ip = _ip;
        port = _port;
    }

    public void setListener(NetworkCallbacks _callbacks) {
        callbacks = _callbacks;
    }

    @Override
    public void run() {
        try {
            Log.e("clientdualantenna", "Connecting...");
            Log.e("clientdualantenna", ip + " " + port);

            socket = new Socket(ip, port);

            callbacks.onConnect();

            sender = new Sender(socket.getOutputStream());
            receiver = new Receiver(socket, socket.getInputStream(), callbacks);

            new Thread(sender).start();
            new Thread(receiver).start();
        } catch (IOException e) {
                Log.e("clientdualantenna", "Connecting failed");
                Log.e("clientdualantenna", e.getMessage());
        }
    }
}