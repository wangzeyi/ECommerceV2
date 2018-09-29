package com.example.wang_.ecommercev2.profile;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;

public class ProfileActivity extends AppCompatActivity {

    TextView textView_usr, textView_email;
    Toolbar toolbar;
    Button button_Save;
    EditText editText_Profile;
    ImageView imageView_Profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView_usr = findViewById(R.id.textView_profile_usr);
        textView_email = findViewById(R.id.textView_profile_email);
        button_Save = findViewById(R.id.button_save);
        editText_Profile = findViewById(R.id.editText_profilepic);
        imageView_Profile = findViewById(R.id.imageView_profile);



        toolbar = findViewById(R.id.toolbar_profile);
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);



        //SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        textView_usr.setText("Andy Wang");
        textView_email.setText("wangze131@gmail.com");

    }
}
