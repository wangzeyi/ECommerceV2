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

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyViewHolder>{

    List<MyProduct> myList;
    MyProductAdapter.MyProductOnClickListener listener;

    public MyProductAdapter(List<MyProduct> myList, MyProductOnClickListener listener) {
        this.myList = myList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);


        return new MyProductAdapter.MyViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyProduct myProduct = myList.get(position);
        Picasso.with(holder.context).load(myProduct.image).into(holder.imageView_Product);
        holder.textView_Description.setText(myProduct.discription);
        holder.textView_Title.setText(myProduct.pname);
        holder.bind(myProduct, listener);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_Product;
        TextView textView_Description, textView_Title;
        Context context;

        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            imageView_Product = itemView.findViewById(R.id.imageView_product);
            textView_Description = itemView.findViewById(R.id.textView_description);
            textView_Title = itemView.findViewById(R.id.textView_card_title);
            this.context = ctx;
        }

        public void bind(final MyProduct myProduct, final MyProductAdapter.MyProductOnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(myProduct);
                }
            });
        }

    }

    public interface MyProductOnClickListener{
        void onItemClick(MyProduct myProduct);
    }


}
