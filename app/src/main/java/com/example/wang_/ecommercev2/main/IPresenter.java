package com.example.wang_.ecommercev2.main;

import android.view.View;

public interface IPresenter {

    void onClickHandler(View view);
    void passRegister(String info);
    void passLogin(String info);
}
