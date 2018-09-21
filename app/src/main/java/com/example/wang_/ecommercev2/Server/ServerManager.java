package com.example.wang_.ecommercev2.Server;

public class ServerManager implements IServerManager{

    IServerHelper serverHelper;

    public ServerManager() {
        serverHelper = new ServerHelper();
    }

    @Override
    public void passLogin(String url, String info, onResponseListener listener) {
        serverHelper.passLogin(url, info, listener);
    }
}
