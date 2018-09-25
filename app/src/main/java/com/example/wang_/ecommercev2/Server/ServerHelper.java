package com.example.wang_.ecommercev2.Server;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServerHelper implements IServerHelper{

    String mobile, pwd;
    String id, appapikey;


    public ServerHelper() {
    }



    @Override
    public void passLogin(String url, String info, final IServerManager.onResponseListener listener) {

        String[] info_split = info.split(" ");
        mobile = info_split[0];
        pwd = info_split[1];

        //URL_LOGIN =   "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php";
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
                            //Log.d("MyTag", response.toString());
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("id");
                            appapikey = jsonObject.getString("appapikey ");
                            //Log.d("MyTag", id + " " + appapikey);

                            String info_login = id + " " + appapikey;
                            //Log.d("TagLogin", info_login);
                            listener.gotoCategory(info_login);
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

    @Override
    public void loadSubCategory(String url, final IServerManager.onSubProductListener listener) {

        //Log.d("MySub", url);

        final JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("subcategory");
                    List<SubEProduct> mylist = new ArrayList<>();
                    SubEProduct subEProduct;

                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject product = jsonArray.getJSONObject(i);
                        String scid = product.getString("scid");
                        String scname = product.getString("scname");
                        String scdiscription = product.getString("scdiscription");
                        String scimageurl = product.getString("scimageurl");

                        subEProduct = new SubEProduct(scid, scname, scdiscription, scimageurl);
                        //mylist.add(subEProduct);
                        listener.addSubEProduct(subEProduct);
                        Log.d("MySub", scid+" "+ scname +" "+scdiscription+" "+ scimageurl);

                    }
                    //listener.add

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MySub", "Error!");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MySub", "Error");
            }
        });

        AppController.getInstance().addToRequestQueue(request);

    }

    @Override
    public void loadProduct(String url, final IServerManager.onProductListener listener) {

        final JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("MyProdcut", response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("products");
                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject product = jsonArray.getJSONObject(i);
                        String id = product.getString("id");
                        String pname = product.getString("pname");
                        String quantity = product.getString("quantity");
                        String prize = product.getString("prize");
                        String discription = product.getString("discription");
                        String image = product.getString("image");
                        MyProduct myProduct = new MyProduct(id,pname,quantity,prize,discription,image);
                        listener.addProduct(myProduct);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MySub", "Error!");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MySub", "Error");
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }
}
