package com.example.wang_.ecommercev2.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.main.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements IViewRegister{

    EditText editText_fn, editText_ln, editText_add, editText_pwd, editText_email, editText_mobile;
    String fn, ln, address, pwd, email, mobile;
    Button button_reg;
    String error_msg;
    IPresenterRegister presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_fn = findViewById(R.id.editText_register_fn);
        editText_ln = findViewById(R.id.editText_register_ln);
        editText_add = findViewById(R.id.editText_register_add);
        editText_pwd = findViewById(R.id.editText_register_password);
        editText_email = findViewById(R.id.editText_register_email);
        editText_mobile = findViewById(R.id.editText_register_mobile);
        button_reg = findViewById(R.id.button_reg);

        presenter = new PresenterRegister(this);
        //error_msg = new String();

        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error_msg = new String();

                fn = editText_fn.getText().toString();
                ln = editText_ln.getText().toString();
                address = editText_add.getText().toString();
                pwd = editText_pwd.getText().toString();
                email = editText_email.getText().toString();
                mobile = editText_mobile.getText().toString();

                Pattern p_email = Pattern.compile("[\\S&&[^@]]*@[\\w]*\\.com$");
                Matcher m_email = p_email.matcher(email);
                boolean b_email = m_email.matches();
                if(!b_email){
                    error_msg= error_msg.concat("Invalid Email \n ");
                }

                Pattern p_fn = Pattern.compile("[\\w]*");
                Matcher m_fn = p_fn.matcher(fn);
                boolean b_fn = m_fn.matches();
                if(!b_fn){
                    error_msg= error_msg.concat("Invalid FirstName \n");
                }

                Pattern p_ln = Pattern.compile("[\\w]*");
                Matcher m_ln = p_ln.matcher(ln);
                boolean b_ln = m_ln.matches();
                if(!b_ln){
                    error_msg= error_msg.concat("Invalid LastName \n");
                }

                Pattern p_pwd_len = Pattern.compile("[\\S]{6,}");
                Matcher m_pwd_len = p_pwd_len.matcher(pwd);
                boolean b_pwd_len = m_pwd_len.matches();
                if(!b_pwd_len){
                    error_msg= error_msg.concat("Password is too short \n");
                }

                Pattern p_pwd_spe = Pattern.compile("[\\S]*[\\W][\\S]*");
                Matcher m_pwd_spe = p_pwd_spe.matcher(pwd);
                boolean b_pwd_spe = m_pwd_spe.matches();
                if(!b_pwd_spe){
                    error_msg= error_msg.concat("Password must contain special char \n");
                }

                Pattern p_mobile = Pattern.compile("[\\d]{10,11}");
                Matcher m_mobile = p_mobile.matcher(mobile);
                boolean b_mobile = m_mobile.matches();
                if(!b_mobile){
                    error_msg= error_msg.concat("Invalid Mobile \n");
                }

                if(b_fn&&b_ln&&b_pwd_len&&b_email&&b_pwd_spe&&b_mobile ){
//http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php
//http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?fname=aamir&lname=husain
// &address=noida&email=aa@gmail.com&mobile=55565454&password=7011
                    String url_login = MyURL.URL_REGISTER+"?fname="+fn+"&lname="+ln+
                                       "&address="+address+"&email="+email+"&mobile="+mobile+
                                       "&password="+pwd;

                    presenter.goLogin(url_login);
                }
                else{
                    Toast.makeText(RegisterActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    @Override
    public void registerSuccess(String reg_info) {
        if(reg_info.equals("successfully registered")){
           Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
           Intent i = new Intent(RegisterActivity.this, MainActivity.class);
           startActivity(i);
        }
        else{
            Toast.makeText(this, reg_info, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerFail(String error_info) {
        Toast.makeText(this, error_info, Toast.LENGTH_SHORT).show();
    }
}
