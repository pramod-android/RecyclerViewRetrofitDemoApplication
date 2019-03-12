package com.example.recyclerviewdemoapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context mContext;
    LayoutInflater mInflater;
    List<Product> productList;
    ItemClickListener mClickListener;

    public RecyclerViewAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.productList = productList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtProductName.setText(productList.get(position).getProductName());
        Picasso.get().load(productList.get(position).getImageUrl()).into(holder.imgProductImage);
       // Picasso.with(mContext).load(mData.get(position).getImageUrl()).fit().into(holder.imgProductImage);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtProductName;

        ImageView imgProductImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.textView);
            imgProductImage = itemView.findViewById(R.id.imageView);
            txtProductName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
             if (mClickListener != null) {
                 if (view.getId()==txtProductName.getId()){
                     mClickListener.onItemNameClick(view,getAdapterPosition());
                 }else {
                     mClickListener.onItemClick(view, getAdapterPosition());
                 }
             }
        }
    }

    // convenience method for getting data at click position
    Product getItem(int id) {
        return productList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onItemNameClick(View view, int position);
    }
}
