package com.heydude.server.client;

import com.heydude.server.HeyDudeServer;

public class HeyDudeClient extends BasicClient {

    public HeyDudeClient(HeyDudeServer pServer) {
        super(pServer);
    }

    @Override
    public void onSendToFriend(ClientRequest pRequest) {
        sendMessageTo(pRequest.toId(), pRequest.getMessage());
    }

    @Override
    public void onRecieveMessage(ClientRequest pRequest) {
        getConnection().sendRequest(pRequest);
    }
}
