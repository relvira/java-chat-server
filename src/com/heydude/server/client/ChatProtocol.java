package com.heydude.server.client;

import java.io.Serializable;

public class ChatProtocol implements Serializable{
    public final String Value;

    private ChatProtocol(String pValue) { this.Value = pValue; }

    @Override
    public String toString() { return Value; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatProtocol) {
            return this.toString().equals(obj.toString());
        } else {
            throw new IllegalArgumentException("Debes pasar como argumento un protocolo.");
        }
    }

    public static final ChatProtocol CONNECT = new ChatProtocol("CONNECT");
    public static final ChatProtocol DISCONNECT = new ChatProtocol("DISCONNECT");
    public static final ChatProtocol SEND_MESSAGE = new ChatProtocol("SEND_MESSAGE");
    public static final ChatProtocol RECEIVE_MESSAGE = new ChatProtocol("RECEIVE_MESSAGE");
}
