package com.example.wang_.ecommercev2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang_.ecommercev2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubProductAdapter extends RecyclerView.Adapter<SubProductAdapter.MyViewHolder>{

    List<SubEProduct> myList;
    SubProductAdapter.SubEProductOnClickListener listener;

    public SubProductAdapter(List<SubEProduct> myList, SubEProductOnClickListener listener) {
        this.myList = myList;
        this.listener = listener;
        //Log.d("MyAdapter", "hi");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_item_layout, parent, false);
        return new SubProductAdapter.MyViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SubEProduct subEProduct = myList.get(position);
        //Log.d("MyAdapter", subEProduct.scdiscription);
        Picasso.with(holder.context).load(subEProduct.scimageurl).into(holder.imageView_Product);
        //holder.textView_Description.setText(subEProduct.scdiscription);
        holder.textView_Title.setText(subEProduct.scname);
        holder.bind(subEProduct, listener);

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
            imageView_Product = itemView.findViewById(R.id.imageView_subimg);
            textView_Description = itemView.findViewById(R.id.textView_subdis);
            textView_Title = itemView.findViewById(R.id.textView_subtitle);
            this.context = ctx;
        }

        public void bind(final SubEProduct subEProduct, final SubProductAdapter.SubEProductOnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(subEProduct);
                }
            });
        }

    }

    public interface SubEProductOnClickListener{
        void onItemClick(SubEProduct eProduct);
    }
}
