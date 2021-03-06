package com.example.wang_.ecommercev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.productList.ProductActivity;


public class ReceiptActivity extends AppCompatActivity {

    int userid, itemid, quantity;
    String pname, cardtype, last4;
    String order_info;
    TextView textView_receipt, textView_header2, textView_header3;
    SharedPreferences prefs;
    Button button_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        textView_receipt = findViewById(R.id.textView_receipt);
        textView_header2 = findViewById(R.id.textView_header2);
        textView_header3 = findViewById(R.id.textView_header3);
        button_done = findViewById(R.id.button_Done);

        Bundle bundle = getIntent().getExtras();
        order_info = bundle.getString("Order_Info");

        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        String last4 = prefs.getString("last4", "umm");
        String cardtype = prefs.getString("cardtype","umm");

        String[] order_info_split = order_info.split(" ");
        String orderid = order_info_split[0];
        String billing = order_info_split[1];
        String deliver = order_info_split[2];
        String itemid = order_info_split[3];
        String itemname = order_info_split[4];
        String quantity = order_info_split[5];
        String totalprize = order_info_split[6];
        String placedon = order_info_split[7];
        String firstnm = prefs.getString("firstnm","umm");
        String lastnm = prefs.getString("lastnm","umm");

        String msg = "OrderId: "+ orderid + "\n" + "Billing Address: " + billing + "\n" + "Deliver Address: "+ deliver +"\n"+
                     "Item ID: "+ itemid +  "\n" + "ItemName: " + itemname + "\n" + "Quantity: " + quantity + "\n"+
                     "Total Prize: " + totalprize + "\n" + "Placed On: " + placedon +"\n"+"Last 4 digits of Payment Card: "+
                     last4 + "\n" + "Card Type: "+cardtype;


        textView_receipt.setText(msg);

        String msg2 = "Hello "+ firstnm+" "+lastnm;
        textView_header2.setText(msg2);

        String msg3 = "Your Order "+ orderid + " has been shipped!";
        textView_header3.setText(msg3);

        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReceiptActivity.this, CategoryActivity.class);
                startActivity(i);
            }
        });

        //Toast.makeText(this, ""+userid+cardtype, Toast.LENGTH_SHORT).show();
    }
}
