package com.example.kamil.ciripilot;

public interface ConnectionCallback {

    void onSubnetFind(String subnet);
    void onTcpConnect();
    void onMessageSend();
}
