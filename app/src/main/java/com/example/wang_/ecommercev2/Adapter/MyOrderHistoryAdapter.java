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
        holder.textView_history.setText(myOrderHistory.order_info);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_history;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView_history = itemView.findViewById(R.id.textView_history);
        }
    }

}
