package com.heydude.server.client;

/**
 * Interfaz utilizada para los eventos que pueden lanzar los clientes.
 * TODO Los eventos necesarios serán declarados aquí y llamados en la clase ClientConnection
 */
public interface ClientActionRequest {

    public int getID();

    public ClientConnection getConnection();

    public void onConnect(int pId);

    public void onDisconnect();

    public void onSendToFriend(ClientRequest pRequest);

    public void onRecieveMessage(ClientRequest pRequest);
}
