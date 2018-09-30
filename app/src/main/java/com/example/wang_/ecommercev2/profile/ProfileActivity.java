package com.example.wang_.ecommercev2.profile;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements IViewProfile{

    TextView textView_usr, textView_email;
    Toolbar toolbar;
    Button button_Save;
    EditText editText_Profile;
    ImageView imageView_Profile;
    IPresenterProfile presenter;
    SharedPreferences prefs;
    String id, firstnm, lastnm, mobile, email, usernm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView_usr = findViewById(R.id.textView_profile_usr);
        //textView_email = findViewById(R.id.textView_profile_email);
        button_Save = findViewById(R.id.button_save);
        editText_Profile = findViewById(R.id.editText_profilepic);
        imageView_Profile = findViewById(R.id.imageView_profile);
        presenter = new PresenterProfile(this);
        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbar_profile);
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);

        id = prefs.getString("id", "umm");

        //SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        firstnm = prefs.getString("firstnm","umm");
        lastnm = prefs.getString("lastnm","umm");
        usernm = firstnm + " " + lastnm;

        mobile = prefs.getString("mobile","umm");
        email = prefs.getString("email","umm");

        String msg = firstnm+" "+lastnm+"\n"+
                     mobile+"\n" +
                     email;

        textView_usr.setText(msg);
        //textView_email.setText("wangze131@gmail.com");

        loadProfile();

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = editText_Profile.getText().toString();
                presenter.setProfilePic(url, id);
                loadProfile();
            }
        });



    }

    public void loadProfile(){
        String image_url = presenter.existProfile(id);
        if(image_url !=null){
            Picasso.with(this).load(image_url).into(imageView_Profile);
        }
        else{
            imageView_Profile.setImageResource(R.drawable.conan);
        }

    }
}
