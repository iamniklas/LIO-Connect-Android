package de.niklasenglmeier.lioconnect.network;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Receiver implements Runnable {

    Socket socket = null;

    DataInputStream inStream = null;

    NetworkCallbacks callback = null;

    public Receiver(Socket _socket, InputStream _inStream, NetworkCallbacks _callback) {
        socket = _socket;
        inStream = new DataInputStream(_inStream);
        callback = _callback;
    }

    @Override
    public void run() {
        while(!socket.isClosed()) {
            callback.onReceiveMessage(receive());
        }
    }

    public String receive() {
        try {
            return inStream.readUTF();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}