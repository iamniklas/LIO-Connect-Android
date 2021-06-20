package de.niklasenglmeier.lioconnect.network;

public class NetworkManager {
    public static NetworkManager instance = new NetworkManager();

    public boolean available = false;

    private Thread clientThread = null;

    public Client client = null;

    private static final String username = "User";

    NetworkCallbacks callbackObject = null;

    public NetworkManager() {
        String ip = "000raspberry.ddns.net";
        int port = 3333;

        client = new Client(this, callbackObject, ip, port);

        clientThread = new Thread(client);
        clientThread.start();
    }

    public void setListener(NetworkCallbacks _callbacks) {
        callbackObject = _callbacks;
        client.setListener(_callbacks);
    }

    public void queueMessage(String _message) {
        client.sender.queueMessage(_message);
    }
}
