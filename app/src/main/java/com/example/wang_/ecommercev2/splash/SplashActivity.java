package com.example.wang_.ecommercev2.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.main.MainActivity;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);

                    Intent i = new Intent(SplashActivity.this, TechActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();

        //Intent i = new Intent(SplashActivity.this, MainActivity.class);
        //startActivity(i);

    }
}
