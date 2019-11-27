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
import com.wildcardenter.myfab.foodie.models.CartItems;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.List;


/*
                                #  #           #  #     
    Created by Asif Mondal on 18-11-2019 at 23:55
*/


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnItemIteractListener listner;
    private List<CartItems> cartList;


    public CartAdapter(Context context) {
        this.context = context;
    }
    public void setProductList(List<Product> list){
        this.productList=list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        if (productList!=null){
            Product product=productList.get(position);
            Picasso.with(context).load(product.getImageUrls()).into(holder.thumbnail);
            holder.name.setText(product.getProductName());
            holder.category.setText(product.getCategory());
            holder.price.setText(String.valueOf(product.getProductPrice()));
            if (cartList!=null){
                if (cartList.get(position)!=null){
                    holder.item_count.setText(String.valueOf(cartList.get(position).getItemCount()));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList!=null?productList.size():0;
    }

    public void setCartList(List<CartItems> li) {
        this.cartList=li;
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView name,category,price,item_count;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.cart_item_thumbnail);
            name=itemView.findViewById(R.id.cart_item_name);
            price=itemView.findViewById(R.id.cart_item_price);
            category=itemView.findViewById(R.id.cart_item_category);
            item_count=itemView.findViewById(R.id.item_count);
            itemView.findViewById(R.id.cart_item_plus).setOnClickListener(i->{
                if (listner!=null){
                    int pos=getAdapterPosition();
                    listner.onInteract(pos,OnItemIteractListener.PLUS);
                }
            });
            itemView.findViewById(R.id.cart_item_minus).setOnClickListener(i->{
                if (listner!=null){
                    int pos=getAdapterPosition();
                    listner.onInteract(pos,OnItemIteractListener.MINUS);
                }
            });
        }
    }

    public void setOnItemInteract(OnItemIteractListener listener){
        this.listner=listener;
    }
    public interface OnItemIteractListener{
        int PLUS=1,MINUS=-1;
        void onInteract(int position,int what);
    }
}
