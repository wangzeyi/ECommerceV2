package com.example.wang_.ecommercev2.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.MyViewHolder>{

    List<OrderProduct> mylist;
    OrderProductAdapter.MyOrderOnClickListener listener;


    public OrderProductAdapter(List<OrderProduct> mylist, OrderProductAdapter.MyOrderOnClickListener listener) {
        this.mylist = mylist;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_item_layout, parent, false);
        return new OrderProductAdapter.MyViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderProduct orderProduct = mylist.get(position);
        //Log.d("MyAdapter", subEProduct.scdiscription);

        Picasso.with(holder.context).load(orderProduct.getImage()).into(holder.imageView_Product);

        int quan = orderProduct.getQuantity();
        holder.textView_Quantity.setText("Quantity: "+Integer.toString(quan));
        holder.textView_Title.setText(orderProduct.getItem_name());
        holder.bind(orderProduct, listener);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_Product;
        TextView textView_Quantity, textView_Title;
        Context context;

        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            imageView_Product = itemView.findViewById(R.id.imageView_subimg);
            textView_Quantity = itemView.findViewById(R.id.textView_subdis);
            textView_Title = itemView.findViewById(R.id.textView_subtitle);
            this.context = ctx;
        }

        public void bind(final OrderProduct orderProduct, final MyOrderOnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(orderProduct);
                }
            });
        }

    }

    public interface MyOrderOnClickListener{
        void onItemClick(OrderProduct orderProduct);
    }

}
