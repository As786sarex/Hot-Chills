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
import com.wildcardenter.myfab.foodie.models.Category;

import java.util.List;


/*
    Class On Package com.wildcardenter.myfab.foodie.adapters
    
    Created by Asif Mondal on 12-10-2019 at 23:33
*/


public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryRecyclerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category=categories.get(position);
        Picasso.with(context).load(category.getImageUrl()).into(holder.image);
        holder.category.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView category;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.category_img);
            category=itemView.findViewById(R.id.category_text);

        }
    }
}
