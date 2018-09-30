package com.example.wang_.ecommercev2.advertisement;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wang_.ecommercev2.R;
import com.squareup.picasso.Picasso;

public class MyAdvertisementActivity extends AppCompatActivity implements IViewAdvertisement{

    ImageView imageView_ad, imageView_headerad;
    Button button_order;
    SharedPreferences prefs;
    IPresenterAdvertisement presenter;

    int[] headerad_array = {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4 };
    String id, firstnm, lastnm, usernm, mobile, email, appapikey, user_info;
    String[] image_array = {"https://rjtmobile.com/ansari/shopingcart/admin/uploads/product_images/images.jpg",
                            "https://rjtmobile.com/ansari/shopingcart/admin/uploads/product_images/mylaptop1.jpg"
    };
    String[] pid_array = {"308", "309"};
    String[] pname_array = { "i5-Laptop",  "HP"};
    String[] prize_array = {"60000",  "40000"};
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_advertisement);

        imageView_ad = findViewById(R.id.imageView_ad);
        imageView_headerad =findViewById(R.id.imageView_headad);
        button_order = findViewById(R.id.button_orderad);
        count = 0;

        presenter = new PresenterAdvertisement(this);

        Picasso.with(this).load(image_array[count]).into(imageView_ad);

        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        id = prefs.getString("id", "umm");
        firstnm = prefs.getString("firstnm","umm");
        lastnm = prefs.getString("lastnm","umm");
        usernm = firstnm + " " + lastnm;
        appapikey = prefs.getString("appapikey", "umm");
        mobile = prefs.getString("mobile","umm");
        email = prefs.getString("email","umm");
        //String p_info = pid+" "+pname+" "+pquantity+" "+prize;
        user_info = id+" "+usernm+" "+mobile+" "+email+" "+appapikey;

        imageView_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = image_array.length;
                count = count + 1;
                while(count >= len){
                   count = count - len;
                }
                Picasso.with(MyAdvertisementActivity.this).load(image_array[count]).into(imageView_ad);

            }
        });

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = image_array.length;
                while(count >= len){
                    count = count - len;
                }
                String p_info = pid_array[count]+" "+pname_array[count]+" "+1+" "+prize_array[count]+" "+image_array[count];
                presenter.saveOrder(user_info, p_info);
            }
        });

        ShowAdTask showAdTask = new ShowAdTask();
        showAdTask.execute();

    }

    class ShowAdTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<10; i++) {
                publishProgress(count++);

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
            int idx = values[0];
            int idx1 = idx % headerad_array.length;
            imageView_headerad.setImageResource(headerad_array[idx1]);
            int idx2 = idx % image_array.length;
            Picasso.with(MyAdvertisementActivity.this).load(image_array[idx2]).into(imageView_ad);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }



}
