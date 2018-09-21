package com.example.wang_.ecommercev2.Server;

import java.net.IDN;

public interface IServerHelper {

    void passLogin(String url, String info, IServerManager.onResponseListener listener);
}
