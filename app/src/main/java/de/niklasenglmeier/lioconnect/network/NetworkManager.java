package de.niklasenglmeier.lioconnect.network;

public class NetworkManager {
    public static NetworkManager instance = new NetworkManager();

    private Thread clientThread = null;

    public Client client = null;

    private static String username = "User";

    private final String ip = "000raspberry.ddns.net";
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
