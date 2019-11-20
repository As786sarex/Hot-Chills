package com.wildcardenter.myfab.foodie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;


/*
                                #  #           #  #     
    Created by Asif Mondal on 18-11-2019 at 20:10
*/


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private Context context;
    private List<Product> products;

    public FavouriteAdapter(Context context) {
        this.context = context;
    }

    public void setProducts(List<Product> products){
        this.products=products;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouriteViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.purchase_favourite_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        if (products!=null){
            Product product=products.get(position);
            Picasso.with(context).load(product.getImageUrls()).into(holder.thumbnail);
            holder.name.setText(product.getProductName());
            holder.price.setText(String.valueOf(product.getProductPrice()));
            holder.category.setText(product.getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return products!=null?products.size():0;
    }


    class FavouriteViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView name,price,category;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.favourite_thumbnail);
            name=itemView.findViewById(R.id.favourite_name);
            price=itemView.findViewById(R.id.favourite_price);
            category=itemView.findViewById(R.id.favourite_category);
        }
    }
}
