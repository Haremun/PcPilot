package com.example.kamil.ciripilot;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpConnection extends Thread {

    private boolean connected = false;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;
    private Socket clientSocket = null;

    @Override
    public void run() {
        super.run();

        try {
            clientSocket = new Socket("192.168.1.6", 8899);
            connected = true;
            Log.i("TcpConnection", "Connected!");
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(final String message) {
        if (connected) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        if (clientSocket.isConnected())
                            Log.i("TcpConnection", "Socket status: connected");
                        else
                            Log.i("TcpConnection", "Socket status: disconnected");
                        Log.i("TcpConnection", "Sending... ");
                        outToServer.writeBytes(message + "\n");
                        //Log.i("TcpConnection", "Waiting for response... ");
                        //Log.i("TcpConnection", "Server Response: "  + inFromServer.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                        connected = false;
                    }
                }
            };
            thread.start();
        }
    }
    public void closeConnection(){
        if(clientSocket != null){
            try {
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
