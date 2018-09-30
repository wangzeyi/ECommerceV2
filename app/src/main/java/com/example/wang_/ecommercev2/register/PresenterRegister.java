package com.example.wang_.ecommercev2.register;

import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.ServerManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterRegister implements IPresenterRegister, IServerManager.onRegisterListener{

    IViewRegister view;
    IServerManager manager;

    public PresenterRegister(RegisterActivity registerActivity) {
        this.view = registerActivity;
        manager = new ServerManager();
    }


    @Override
    public void goLogin(String url) {
        manager.goLogin(url, this);
    }

    @Override
    public void registerSuccess(String reg_info) {
        view.registerSuccess(reg_info);
    }

    @Override
    public void registerFail(String error_info) {
        view.registerFail(error_info);
    }
}
