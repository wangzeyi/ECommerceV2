package com.example.wang_.ecommercev2.Server;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;

import java.util.List;

public interface IServerHelper {

    void passLogin(String url, String info, IServerManager.onResponseListener listener);
    void loadSubCategory(String url, IServerManager.onSubProductListener listener);
    void loadProduct(String url, IServerManager.onProductListener listener);
    void placeOrder(String url, IServerManager.onCheckoutListener listener);
}
