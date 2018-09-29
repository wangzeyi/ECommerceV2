package com.example.wang_.ecommercev2.Server;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;

import java.util.List;

public class ServerManager implements IServerManager{

    IServerHelper serverHelper;

    public ServerManager() {
        serverHelper = new ServerHelper();

    }

    @Override
    public void passLogin(String url, String info, onResponseListener listener) {
        serverHelper.passLogin(url, info, listener);
    }

    @Override
    public void loadSubCategory(String url, onSubProductListener listener) {
        serverHelper.loadSubCategory(url, listener);
    }

    @Override
    public void loadProduct(String url, onProductListener listener) {
        serverHelper.loadProduct(url, listener);
    }

    @Override
    public void placeOrder(String url, onCheckoutListener listener) {
       serverHelper.placeOrder(url, listener);
    }

    @Override
    public void getOrderHistory(String url, onOrderHistoryListener listener) {
       serverHelper.getOrderHistory(url, listener);
    }


}
