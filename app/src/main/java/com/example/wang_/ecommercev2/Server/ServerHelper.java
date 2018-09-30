package com.example.wang_.ecommercev2.Server;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.utils.AppController;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServerHelper implements IServerHelper{

    String mobile, pwd;
    String id, appapikey, firstnm, lastnm, email;


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
                            firstnm = jsonObject.getString("firstname");
                            lastnm = jsonObject.getString("lastname");
                            email = jsonObject.getString("email");
                            //Log.d("MyTag", id + " " + appapikey);

                            String info_login = id+" "+appapikey+" "+firstnm+" "+lastnm+" "+email+" "+mobile;
                            //Log.d("TagLogin", info_login);
                            listener.loginSuccess();
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
                listener.loginFail();
            }
        });

        AppController.getInstance().addToRequestQueue(request);

    }

    @Override
    public void goLogin(String url, final IServerManager.onRegisterListener listener) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MyRegister", response.toString());
                listener.registerSuccess(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyRegister", "Error");
                listener.registerFail(error.toString());
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

                Log.d("MyProduct", response.toString());

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

    @Override
    public void placeOrder(String url, final IServerManager.onCheckoutListener listener_check) {

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("MyOrder!", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("Order confirmed");
                    for(int i=0; i<jsonArray.length(); i++){
/**
 *             "orderid": "2147484213",
 "orderstatus": "1",
 "name": "Aamir",
 "billingadd": "Noida",
 "deliveryadd": "Noida",
 "mobile": "4845425346",
 "email": "wangze131@gmail.com",
 "itemid": "701",
 "itemname": "laptop",
 "itemquantity": "1",
 "totalprice": "100000",
 "paidprice": "100000",
 "placedon": "2018-09-27 20:12:02"
 *
 *
 */
                        JSONObject product = jsonArray.getJSONObject(i);
                        //Log.d("MyOrder!", product.toString());
                        String orderid = product.getString("orderid");
                        String billing = product.getString("billingadd");
                        String deliver = product.getString("deliveryadd");
                        String itemid = product.getString("itemid");
                        String itemname = product.getString("itemname");
                        String quantity = product.getString("itemquantity");
                        String totalprize = product.getString("totalprice");
                        String placedon = product.getString("placedon");

                        String order_info = orderid+" "+billing+" "+deliver+" "+itemid+" "+itemname+" "
                                           +quantity+" "+totalprize+" "+placedon;
                        listener_check.getOrderDetail(order_info);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MyCheck", "Error!");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyOrder", "Error");
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void getOrderHistory(String url, final IServerManager.onOrderHistoryListener listener) {

        final JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Log.d("MyHistSuccess", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray( "Order history");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject product = jsonArray.getJSONObject(i);
                        /*"orderid": "2147484213",
                         "orderstatus": "1",
                         "name": "Aamir",
                         "billingadd": "Noida",
                         "deliveryadd": "Noida",
                         "mobile": "4845425346",
                         "email": "wangze131@gmail.com",
                         "itemid": "701",
                         "itemname": "laptop",
                         "itemquantity": "1",
                         "totalprice": "100000",
                         "paidprice": "100000",
                         "placedon": "2018-09-27 20:12:02"

                         */
                        //Log.d("MyOrder!", product.toString());
                        String orderid = product.getString("orderid");
                        String orderstatus = product.getString("orderstatus");
                        String name = product.getString("name");
                        String billingad = product.getString("billingadd");
                        String deliveryad = product.getString("deliveryadd");
                        String mobile = product.getString("mobile");
                        String email = product.getString("email");
                        String itemid = product.getString("itemid");
                        String itemq = product.getString("itemquantity");
                        String totalprize = product.getString("totalprice");
                        String placedon = product.getString("placedon");

                        String order_info = orderid+" "+orderstatus+" "+name+" "
                                           +mobile+" "+email+" "+itemid+" "+itemq+" "+totalprize+" "+placedon;
                        listener.returnOrderHistory(order_info);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyHistError", "Error");
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
