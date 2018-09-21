package com.example.wang_.ecommercev2.Server;

public interface IServerManager extends IServerHelper{


    interface onResponseListener{
        void gotoCategory(String s);
    }

}
