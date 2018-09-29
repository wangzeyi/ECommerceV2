package com.example.wang_.ecommercev2.orderhistory;

import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.ServerManager;

public class PresenterOrderHistory implements IPresenterOrderHistory, IServerManager.onOrderHistoryListener {

    IViewOrderHistory view;
    IServerManager manager;

    public PresenterOrderHistory(OrderHistoryActivity orderHistoryActivity) {
        this.view = orderHistoryActivity;
        this.manager = new ServerManager();
    }


    @Override
    public void loadOrderHistory(String url) {
        manager.getOrderHistory(url, this);
    }

    @Override
    public void returnOrderHistory(String order_info) {
        view.returnOderHistory(order_info);
    }
}
