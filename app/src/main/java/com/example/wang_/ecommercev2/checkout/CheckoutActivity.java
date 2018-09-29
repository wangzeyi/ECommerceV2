package com.example.wang_.ecommercev2.checkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.ReceiptActivity;
import com.example.wang_.ecommercev2.data.Contract;
import com.example.wang_.ecommercev2.data.database.MyDataBase;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class CheckoutActivity extends AppCompatActivity implements IViewCheckout {

    private Simplify simplify;
    Button button_pay;
    CardEditor cardEditor;

    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences prefs;
    String userid, firstnm, lastnm, billingad, deliverad, mobile, email, appapikey;
    String pname;
    String user_info, p_info;
    IPresenterCheckout presenter;
    String last4, cardtype;
    EditText editText_Billing, editText_Deliver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        myDataBase = new MyDataBase(CheckoutActivity.this);
        sqLiteDatabase = myDataBase.getWritableDatabase();
        presenter = new PresenterCheckout(CheckoutActivity.this);
        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        editText_Billing = findViewById(R.id.editText_billing);
        editText_Deliver = findViewById(R.id.editText_deliver);

        String key = "sbpb_ODg0MzdmYWMtYjU0NS00YjkwLTllOGMtNjA4ZjhmYmNiZmUz";
        simplify = new Simplify();
        simplify.setApiKey(key);

        button_pay = findViewById(R.id.button_pay);
        cardEditor = findViewById(R.id.card_editor);

        userid = prefs.getString("id","umm");
        firstnm = prefs.getString("firstnm","umm");
        lastnm = prefs.getString("lastnm","umm");
        //billingad = editText_Billing.getText().toString();
        //deliverad = editText_Deliver.getText().toString();
        mobile = prefs.getString("mobile","umm");
        email = prefs.getString("email","umm");
        appapikey = prefs.getString("appapikey","umm");

        //Log.d("MyCheckout", billingad);


        //user_info = userid+" "+firstnm+lastnm+" "+billingad+" "+deliverad+" "+mobile+" "+email+" "+appapikey;

        /**
         * item_id'
         item_names
         item_quantity
         final_price

         user_id
         user_name
         billingadd
         deliveryadd
         mobile
         email

         api_key

         *
         *
         */


        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Log.d("MyTag", "onSuccess: "+cardToken.getCard().getType());

                        billingad = editText_Billing.getText().toString();
                        deliverad = editText_Deliver.getText().toString();
                        user_info = userid+" "+firstnm+lastnm+" "+billingad+" "+deliverad+" "+mobile+" "+email+" "+appapikey;

                        presenter.getOrder(user_info);
                        last4 = cardToken.getCard().getLast4().toString();
                        cardtype = cardToken.getCard().getType().toString();
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

    @Override
    public void setOrderDetail(String order_info) {

        //String order_info = orderid+" "+billing+" "+deliver+" "+itemid+" "+itemname+" "
        //+quantity+" "+totalprize+" "+placedon;
//        String[] order_info_split = order_info.split(" ");
//        String orderid = order_info_split[0];
//        String billing = order_info_split[1];
//        String deliver = order_info_split[2];
//        String itemid = order_info_split[3];
//        String itemname = order_info_split[4];
//        String quantity = order_info_split[5];
//        String totalprize = order_info_split[6];
//        String placedon = order_info_split[7];
        clearDatabase();
        Intent i = new Intent(CheckoutActivity.this, ReceiptActivity.class);
        i.putExtra("Order_Info", order_info+" "+last4+" "+cardtype);
        startActivity(i);

    }
}
