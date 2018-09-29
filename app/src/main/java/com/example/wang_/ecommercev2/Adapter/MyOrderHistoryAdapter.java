package com.example.wang_.ecommercev2.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;

import java.util.List;

public class MyOrderHistoryAdapter extends RecyclerView.Adapter<MyOrderHistoryAdapter.MyViewHolder>{

    List<MyOrderHistory> myList;

    public MyOrderHistoryAdapter(List<MyOrderHistory> myList) {
        this.myList = myList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_layout, parent, false);
        return new MyOrderHistoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyOrderHistory myOrderHistory = myList.get(position);
        //                        String order_info = orderid+" "+orderstatus+" "+name+" "
        //                                           +mobile+" "+email+" "+itemid+" "+itemq+" "+totalprize+" "+placedon;
        String order_info = myOrderHistory.order_info;
        String[] order_info_split = order_info.split(" ");
        String orderid = order_info_split[0];
        String orderstatus = order_info_split[1];
        String name = order_info_split[2];
        String mobile = order_info_split[3];
        String email = order_info_split[4];
        String itemid = order_info_split[5];
        String itemq = order_info_split[6];
        String totalprize = order_info_split[7];
        String placedon = order_info_split[8];

        String order_detail = "Order ID: "+orderid + "\n" + "Order Status: "+ orderstatus+"\n" +
                              "Item ID: "+itemid + "\n" + "Item Quantity: "+itemq +"\n"+
                              "Total Prize: "+totalprize+"\n" + "Placed on: "+placedon;

        String user_detail = "User Name: "+ name + "\n" + "Mobile: "+mobile+"\n"+ "Email: "+email;

        holder.textView_order.setText(order_detail);
        holder.textView_user.setText(user_detail);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_order;
        TextView textView_user;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView_order = itemView.findViewById(R.id.textView_orderinfo);
            textView_user = itemView.findViewById(R.id.textView_userinfo);
        }
    }

}
