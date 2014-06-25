package com.heydude.client;

import com.google.gson.Gson;
import com.heydude.server.client.ClientRequest;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Runnable{
    private PrintWriter mOut;
    private BufferedReader mIn;
    private BufferedReader mBufferedReader;

    private ClientRequest mRequest;

    private Gson gson;

    public Client() {
        try {
            Socket socket = new Socket("localhost", 8000);
            this.mOut = new PrintWriter(socket.getOutputStream());
            this.mOut.flush();
            this.mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.mBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson = new Gson();

        Thread thread = new Thread(this);
        thread.start();

//        do {
//            String msg;
//            try {
//                System.out.println("Debug >> Send a message");
//                msg = mBufferedReader.readLine();
//                if (msg.equals("exit")) {
//                    mRequest = new ClientRequest(Protocol_NO.DISCONNECT, msg);
//                } else {
//                    mRequest = new ClientRequest(Protocol_NO.SEND_MESSAGE, msg);
//                }
//                System.out.println(gson.toJson(mRequest));
//                mOut.println(gson.toJson(mRequest));
//                mOut.flush();
//            } catch (IOException e) {
//                mRequest = new ClientRequest(Protocol_NO.DISCONNECT);
//                e.printStackTrace();
//            }
//        } while (mRequest.getChatProtocol() != Protocol_NO.DISCONNECT);
    }

    @Override
    public void run() {
        boolean exit = false;
        do {
            try {
                mRequest = gson.fromJson(mIn.readLine(), ClientRequest.class);
                System.out.println("Server >> " + mRequest.getMessage());
            }catch (SocketException e) {
                System.out.println("Disconnected from server.");
                exit = true;
            } catch (IOException e) {
                e.printStackTrace();
                exit = true;
            }
        } while(!exit);
    }
}
