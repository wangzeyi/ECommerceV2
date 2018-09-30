package com.example.wang_.ecommercev2.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.register.RegisterActivity;

public class MainActivity extends AppCompatActivity implements IView {

    EditText txt_Pwd, txt_Mobile;
    Button button_Register, button_Login;
    IPresenter presenter;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Pwd = findViewById(R.id.editText_pwd);
        txt_Mobile = findViewById(R.id.editText_mobile);
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("Old Navy");
        setSupportActionBar(toolbar);


        button_Register = findViewById(R.id.button_register);
        button_Login = findViewById(R.id.button_login);

        presenter = new Presenter(MainActivity.this);

        editor = getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();


    }


    @Override
    public void gotoRegister() {
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(i);
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
    public void loginSuccess() {
        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoCategory(String info_login) {

        String[] info_login_split = info_login.split(" ");

        Log.d("Login Info", info_login);

        String id = info_login_split[0];
        String appapikey = info_login_split[1];
        String firstnm = info_login_split[2];
        String lastnm = info_login_split[3];
        String email = info_login_split[4];
        String mobile = info_login_split[5];

        editor.putString("id", id);
        editor.putString("appapikey", appapikey);
        editor.putString("firstnm", firstnm);
        editor.putString("lastnm", lastnm);
        editor.putString("email", email);
        editor.putString("mobile",mobile);
        editor.commit();

        Intent i = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(i);
    }

    @Override
    public void validationFail() {
        Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
    }

    public void onClickHandler(View view) {
        presenter.onClickHandler(view);
    }



}
