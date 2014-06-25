package com.heydude.server;

import com.heydude.server.client.ClientActionRequest;
import com.heydude.server.client.HeyDudeClient;

import java.util.ArrayList;
import java.util.List;

public class HeyDudeServer {

    private List<ClientActionRequest> mClients;

    public HeyDudeServer() {
        mClients = new ArrayList<>();

        while(true) {
            System.out.println("Waiting clients...");
            mClients.add(new HeyDudeClient(this));
        }
    }

    public List<ClientActionRequest> getClients() {
        return mClients;
    }
}
