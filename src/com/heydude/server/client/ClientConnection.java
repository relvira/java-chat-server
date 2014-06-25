package com.heydude.server.client;

import com.google.gson.Gson;
import com.heydude.server.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private BufferedReader mInput;
    private PrintWriter mOutput;

    private Socket mSocket;

    private boolean exit;

    private ClientRequest mRequest;

    private Thread mThrInitCommunication;

    private ClientActionRequest mRequestListener;

    private Gson gson;

    public ClientConnection(ClientActionRequest pHeyDudeClient) {
        gson = new Gson();
        mRequestListener = pHeyDudeClient;
        try {
            mSocket = ServerConnection.accept();
            initBuffers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recieveMessagesFromClient();
    }

    private void initBuffers() throws IOException {
        mOutput = new PrintWriter(mSocket.getOutputStream());
        mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
    }

    public void recieveMessagesFromClient() {
        mThrInitCommunication = new Thread(this);
        mThrInitCommunication.start();
    }

    @Override
    public void run() {
        while (!exit) {
            mRequest = readRequest();

            if (mRequest.getChatProtocol().equals(ChatProtocol.CONNECT)){
                mRequestListener.onConnect(mRequest.fromId());
            } else if (mRequest.getChatProtocol().equals(ChatProtocol.SEND_MESSAGE)) {
                mRequestListener.onSendToFriend(mRequest);
            } else if (mRequest.getChatProtocol().equals(ChatProtocol.RECEIVE_MESSAGE)) {
                mRequestListener.onRecieveMessage(mRequest);
            } else if (mRequest.getChatProtocol().equals(ChatProtocol.DISCONNECT)) {
                mRequestListener.onDisconnect();
                exit = true;
            } else {
                exit = true;
                break;
            }
        }
    }

    private ClientRequest readRequest() {
        try {
            // Todo: borrar esto
            String json = mInput.readLine();
            System.out.println("Json: " + json);
            return gson.fromJson(json, ClientRequest.class);
            //return gson.fromJson(mInput.readLine(), ClientRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClientRequest(mRequestListener.getID(), ChatProtocol.DISCONNECT);
    }

    public void sendRequest(ClientRequest pRequest) {
        mOutput.println(gson.toJson(pRequest));
        mOutput.flush();
    }

    public BufferedReader getInput() {
        return mInput;
    }

    public PrintWriter getOutput() {
        return mOutput;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mSocket.close();
        mThrInitCommunication.interrupt();
    }
}
