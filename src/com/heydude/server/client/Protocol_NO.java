package com.heydude.server.client;

public enum Protocol_NO {
    CONNECT("CONNECT"),
    DISCONNECT("DISCONNECT"),
    SEND_MESSAGE("SEND_MESSAGE"),
    RECEIVE_MESSAGE("RECEIVE_MESSAGE");

    private final String Value;

    Protocol_NO(String pValue) {
        Value = pValue;
    }

    public String getValue() {
        return Value;
    }
}
