package com.example.wang_.ecommercev2.main;

public interface IView {

    void gotoRegister();
    void passLogin();
    void loginSuccess();
    void loginFail();
    void gotoCategory(String info_login);
    void validationFail();
}
