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
    Class On Package com.wildcardenter.myfab.foodie.adapters
    
    Created by Asif Mondal on 13-10-2019 at 11:53
*/


public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.FoodMenuViewHolder> {

    private Context context;
    List<Product> products;
    private OnClickListener onClickListener;

    public FoodMenuAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
    public interface OnClickListener{
        void onClick(int position);

    }
    public void setOnClickListener(OnClickListener listener){
        this.onClickListener=listener;
    }

    @NonNull
    @Override
    public FoodMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.food_menu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenuViewHolder holder, int position) {
        Product p=products.get(position);
        Picasso.with(context).load(p.getImageUrls()).into(holder.image);
        holder.name.setText(p.getProductName());
        holder.price.setText( String.valueOf(p.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class FoodMenuViewHolder extends RecyclerView.ViewHolder{
        View cardview;
        ImageView image;
        TextView price,name;
        FoodMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.food_menu_image);
            price=itemView.findViewById(R.id.foodmenu_price);
            name=itemView.findViewById(R.id.foodmenu_name);
            cardview=itemView.findViewById(R.id.food_item_container);
            cardview.setOnClickListener(v -> {
                if (onClickListener!=null){
                    int pos=getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION){
                        onClickListener.onClick(pos);
                    }
                }
            });
        }
    }
}
