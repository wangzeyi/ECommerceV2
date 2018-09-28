package com.example.wang_.ecommercev2.productList;

import android.util.Log;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.IServerHelper;
import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.ServerHelper;
import com.example.wang_.ecommercev2.Server.ServerManager;
import com.example.wang_.ecommercev2.data.DBManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterProduct implements IPresenterProduct, IServerManager.onProductListener, IDBManager.onSaveListener{

    IViewProduct view;
    IServerManager manager;
    IDBManager dbManager;

    public PresenterProduct(ProductActivity productActivity) {
        this.view = productActivity;
        manager = new ServerManager();
        dbManager = new DBManager(productActivity);
    }

    @Override
    public void loadProduct(String url) {
        manager.loadProduct(url, this);
    }

    @Override
    public void saveOrder(String user_info, String p_info) {
        dbManager.saveOrder(user_info, p_info,this);
    }

    @Override
    public void onclick(View v) {

    }

    @Override
    public void addProduct(MyProduct myProduct) {
        view.addProduct(myProduct);
    }



}
