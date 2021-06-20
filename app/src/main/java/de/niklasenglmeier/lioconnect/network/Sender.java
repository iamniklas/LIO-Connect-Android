package de.niklasenglmeier.lioconnect.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Sender implements Runnable {
    DataOutputStream outStream;

    private boolean resourceBlocked = false;

    ArrayList<String> messageBuffer = new ArrayList<>();

    public Sender(OutputStream _outStream) {
        outStream = new DataOutputStream(_outStream);
    }

    public void queueMessage(String _message) {
        if(!resourceBlocked) {
            resourceBlocked = true;
            messageBuffer.add(_message);
            resourceBlocked = false;
        }
    }

    synchronized void send(String _message) {
        try {
            outStream.writeUTF(_message);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        while (true) {
            if(!resourceBlocked) {
                while (messageBuffer.size() > 0) {
                    resourceBlocked = true;
                    send(messageBuffer.get(0));
                    messageBuffer.remove(0);
                    resourceBlocked = false;
                }
            }
        }
    }
}