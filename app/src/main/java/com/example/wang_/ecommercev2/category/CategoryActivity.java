package com.example.wang_.ecommercev2.category;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.ServerHelper;

public class CategoryActivity extends AppCompatActivity {
    String id, appapikey;
    ServerHelper serverHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        serverHelper = new ServerHelper();

        SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        id = prefs.getString("id", "umm");
        appapikey = prefs.getString("appapikey", "umm");
        //Log.d("Tag2", id + " " + appapikey);

        serverHelper.setId(id);
        serverHelper.setAppapikey(appapikey);



    }
}
