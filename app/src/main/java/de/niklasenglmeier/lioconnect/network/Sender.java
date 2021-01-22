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

    synchronized void sendByte(byte _byte) {
        try {
            outStream.writeByte(_byte);

            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void sendBinary(String _message) {
        try {
            byte messageLength = (byte) _message.length();
            byte[] msgBinary = _message.getBytes();

            //outStream.writeByte(messageLength);

            for (int i = 0; i < messageLength; i++) {
                outStream.writeByte(msgBinary[i]);
            }

            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        sendByte((byte) 3);
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