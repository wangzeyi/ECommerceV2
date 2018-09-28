package com.example.wang_.ecommercev2.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.main.MainActivity;

public class TechActivity extends AppCompatActivity {

    Button button_ToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);

        button_ToMain = findViewById(R.id.button_tomain);
        button_ToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TechActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
