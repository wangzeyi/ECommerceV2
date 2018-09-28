package com.example.wang_.ecommercev2.main;


import android.content.Intent;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.Server.ServerManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String[] info_split = info.split(" ");
        String mobile = info_split[0];
        Pattern p_mobile = Pattern.compile("[\\d]{10}");
        Matcher m_mobile = p_mobile.matcher(mobile);
        boolean b_mobile = m_mobile.matches();

        if(b_mobile) {
            serverManager.passLogin(MyURL.URL_LOGIN, info, this);
        }
        else{
            view.validationFail();
        }
    }


    @Override
    public void gotoCategory(String info_login) {
        view.gotoCategory(info_login);
    }

    @Override
    public void loginSuccess() {
        view.loginSuccess();
    }

    @Override
    public void loginFail() {
        view.loginFail();
    }


}
