package com.example.wang_.ecommercev2.Server;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.wang_.ecommercev2.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServerHelper implements IServerHelper{

    String mobile, pwd;
    String id, appapikey;

    public ServerHelper() {
    }

    @Override
    public void passLogin(String url, String info, IServerManager.onResponseListener listener) {

        String[] info_split = info.split(" ");
        mobile = info_split[0];
        pwd = info_split[1];

        //url_login =   "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php";
        //url_mylogin = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?mobile=131&password=123"
        String url_mylogin = url + "?" + "mobile=" + mobile + "&password=" + pwd;

        JsonArrayRequest request = new JsonArrayRequest(url_mylogin, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
/**
 "msg": "success",
 "id": "1393",
 "firstname": "zeyi",
 "lastname": "wang",
 "email": "andy@123.com",
 "mobile": "131",
 "appapikey ": "0519f65b870f1f5261e82f207a79b123"
 *
 */
                    for (int i = 0; i < response.length(); i++) {
                        //Log.d("MyTag", response.toString());

                        try {
                            Log.d("MyTag", response.toString());
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("id");
                            appapikey = jsonObject.getString("appapikey ");
                            Log.d("MyTag", id + " " + appapikey);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyTag", "Error");
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }
}
