package com.example.wang_.ecommercev2.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.category.CategoryActivity;

public class MainActivity extends AppCompatActivity implements IView {

    EditText txt_Pwd, txt_Mobile;
    Button button_Register, button_Login;
    IPresenter presenter;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Pwd = findViewById(R.id.editText_pwd);
        txt_Mobile = findViewById(R.id.editText_mobile);

        button_Register = findViewById(R.id.button_register);
        button_Login = findViewById(R.id.button_login);

        presenter = new Presenter(MainActivity.this);

        editor = getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();


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
        //Intent i = new Intent(MainActivity.this, CategoryActivity.class);
        //startActivity(i);
    }

    @Override
    public void gotoCategory(String info_login) {

        String[] info_login_split = info_login.split(" ");

        String id = info_login_split[0];
        String appapikey = info_login_split[1];

        editor.putString("id", id);
        editor.putString("appapikey", appapikey);
        editor.commit();

        Intent i = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(i);
    }

    public void onClickHandler(View view) {
        presenter.onClickHandler(view);
    }



}
