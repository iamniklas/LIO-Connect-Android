package de.niklasenglmeier.lioconnect.network;

public class NetworkManager {
    public static NetworkManager instance = new NetworkManager();

    private Thread clientThread = null;

    public Client client = null;

    private static String username = "User";

    //192.168.178.60 - 3333
    //192.168.178.71 - 2400
    private final String ip = "192.168.178.60";
    private final int port = 3333;

    NetworkCallbacks callbackObject = null;

    public NetworkManager() {
        client = new Client(callbackObject, ip, port);

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
