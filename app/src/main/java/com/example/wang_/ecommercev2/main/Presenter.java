package com.example.wang_.ecommercev2.main;


import android.content.Intent;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.Server.ServerManager;

public class Presenter implements IPresenter, IServerManager.onResponseListener{

    IView view;
    ServerManager serverManager;


    public Presenter(MainActivity mainActivity) {
        this.view = mainActivity;
        this.serverManager = new ServerManager();
    }

    @Override
    public void onClickHandler(View v) {
        switch(v.getId()){
            case R.id.button_register:
                view.passRegister();
                break;
            case R.id.button_login:
                view.passLogin();
                break;
            default:
                break;
        }
    }



    @Override
    public void passRegister(String info) {


    }

    @Override
    public void passLogin(String info) {
        serverManager.passLogin(MyURL.URL_LOGIN, info, this);
    }


    @Override
    public void gotoCategory(String info_login) {
        view.gotoCategory(info_login);
    }


}
