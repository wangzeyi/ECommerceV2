package com.example.wang_.ecommercev2.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wang_.ecommercev2.R;

public class MainActivity extends AppCompatActivity implements IView {

    EditText txt_Pwd, txt_Mobile;
    Button button_Register, button_Login;
    IPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Pwd = findViewById(R.id.editText_pwd);
        txt_Mobile = findViewById(R.id.editText_mobile);

        button_Register = findViewById(R.id.button_register);
        button_Login = findViewById(R.id.button_login);

        presenter = new Presenter(MainActivity.this);

    }


    @Override
    public void showLog(String s) {
        Log.d("MyTagMain", s);
    }

    @Override
    public void passRegister() {
        String pwd = txt_Pwd.getText().toString();
        String mobile = txt_Mobile.getText().toString();
        String info = mobile + " " + pwd;
        presenter.passRegister(info);
    }

    @Override
    public void passLogin() {
        String pwd = txt_Pwd.getText().toString();
        String mobile = txt_Mobile.getText().toString();
        String info = mobile + " " + pwd;
        presenter.passLogin(info);
    }

    public void onClickHandler(View view) {
        presenter.onClickHandler(view);
    }


}
