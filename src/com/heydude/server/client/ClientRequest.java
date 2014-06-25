package com.heydude.server.client;


import java.io.Serializable;

/**
 * Clase contenedora de las peticiones que hacen los clientes.
 */
public class ClientRequest implements Serializable{
    private int FromId;
    private ChatProtocol ChatProtocol;
    private int ToId;
    private String Message;

    public ClientRequest(int pFrom, ChatProtocol pChatProtocol) {
        this(pFrom, pChatProtocol, 0, "");
    }

    public ClientRequest(int pFrom, ChatProtocol pChatProtocol, int pTo, String pMsg) {
        FromId = pFrom;
        ChatProtocol = pChatProtocol;
        ToId = pTo;
        Message = pMsg;
    }

    public ChatProtocol getChatProtocol() {
        return ChatProtocol;
    }

    public int fromId() {
        return FromId;
    }

    public int toId() {
        return ToId;
    }

    public String getMessage() {
        return Message;
    }
}
