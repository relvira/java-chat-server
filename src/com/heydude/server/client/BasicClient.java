package com.heydude.server.client;

import com.heydude.server.HeyDudeServer;

/**
 * Clase que conforma la estructura básica del cliente. Implementa la interfaz
 * necesaria para que se lancen los eventos relacionados con las acciones
 * que puede realizar el cliente.
 * TODO: Se añadirán los campos necesarios de los clientes aquí.
 */
public abstract class BasicClient implements ClientActionRequest {
    private int mID;
    private HeyDudeServer mServer;

    private ClientConnection mClientConnection;

    protected BasicClient(HeyDudeServer pServer) {
        mServer = pServer;
        mClientConnection = new ClientConnection(this);
    }

    /**
     * Evento lanzado cuando el usuario se conecta.
     * TODO: Aquí se debería implantar la propagación a los amigos conectados.
     */
    @Override
    public void onConnect(int pId) {
        mID = pId;
        System.out.println("User " + pId + " connected.");
}

    /**
     * Evento lanzado cuando el usuario se desconecta.
     * TODO: Aquí se debería propagar a los amigos del usuario.
     */
    @Override
    public void onDisconnect() {
        mServer.getClients().remove(this);
    }

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public ClientConnection getConnection() {
        return mClientConnection;
    }

    /**
     * Método encargado de enviar un mensaje pasado como parámetro
     * al cliente específicado.
     * @param pId ID del cliente que va a recibir el mensaje.
     * @param pMessage String que contiene el mensaje.
     */
    protected void sendMessageTo(int pId, String pMessage) {
        ClientRequest request = new ClientRequest(getID(), ChatProtocol.RECEIVE_MESSAGE, pId, pMessage);
        for (ClientActionRequest sameClient : mServer.getClients()) {
            if (sameClient.getID() == pId) {
                sameClient.getConnection().sendRequest(request);
            }
        }
    }

    protected ClientActionRequest findUser(int pId) {
        for (ClientActionRequest h : mServer.getClients()) {
            if (h.getID() == pId) {
                return h;
            }
        }
        return null;
    }
}
