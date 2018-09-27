package com.example.wang_.ecommercev2.checkout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.data.Contract;
import com.example.wang_.ecommercev2.data.database.MyDataBase;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class CheckoutActivity extends AppCompatActivity {

    private Simplify simplify;
    Button button_pay;
    CardEditor cardEditor;

    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        myDataBase = new MyDataBase(CheckoutActivity.this);
        sqLiteDatabase = myDataBase.getWritableDatabase();

        String key = "sbpb_ODg0MzdmYWMtYjU0NS00YjkwLTllOGMtNjA4ZjhmYmNiZmUz";
        simplify = new Simplify();
        simplify.setApiKey(key);

        button_pay = findViewById(R.id.button_pay);
        cardEditor = findViewById(R.id.card_editor);

        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Log.d("MyTag", "onSuccess: "+cardToken.getCard().getType());

                        String msg = "Successful Payment by your " + cardToken.getCard().getType() + "ending with "
                                + cardToken.getCard().getLast4();
                        Toast.makeText(CheckoutActivity.this, msg, Toast.LENGTH_SHORT).show();

                        clearDatabase();

                        Intent i = new Intent(CheckoutActivity.this, CategoryActivity.class);
                        startActivity(i);

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("MyTag", "Error");
                        Toast.makeText(CheckoutActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void clearDatabase(){

        //TRUNCATE TABLE "table_name";
        String cmd = "DELETE FROM " + Contract.Entry.TABLE_NAME;
        sqLiteDatabase.execSQL(cmd);
    }

}
