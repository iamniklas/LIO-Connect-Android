package de.niklasenglmeier.lioconnect.network;

public interface NetworkCallbacks {
    void onConnect();
    void onReceiveMessage(String _message);
}
