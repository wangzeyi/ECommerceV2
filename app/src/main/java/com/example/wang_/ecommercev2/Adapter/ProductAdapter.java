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
import java.util.zip.Inflater;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{

    List<EProduct> myList;

    public ProductAdapter(List<EProduct> myList) {
        this.myList = myList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        EProduct eProduct = myList.get(position);
        Picasso.with(holder.context).load(eProduct.cimagerl).into(holder.imageView_Product);
        holder.textView_Description.setText(eProduct.cdiscription);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_Product;
        TextView textView_Description;
        Context context;

        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            imageView_Product = itemView.findViewById(R.id.imageView_product);
            textView_Description = itemView.findViewById(R.id.textView_description);
            this.context = ctx;
        }
    }

}
