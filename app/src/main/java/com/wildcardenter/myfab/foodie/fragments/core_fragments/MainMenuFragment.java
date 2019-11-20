package com.wildcardenter.myfab.foodie.fragments.core_fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.activities.DetailedFoodActivity;
import com.wildcardenter.myfab.foodie.adapters.CategoryRecyclerAdapter;
import com.wildcardenter.myfab.foodie.adapters.FoodMenuAdapter;
import com.wildcardenter.myfab.foodie.adapters.TrendingSliderAdapter;
import com.wildcardenter.myfab.foodie.models.Category;
import com.wildcardenter.myfab.foodie.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    private List<Product> list;
    private List<Category> categories;
    String picUrl="https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg";
    String picUrl2="https://cdn.pixabay.com/photo/2016/11/29/13/02/cheese-1869708_960_720.jpg";
    public MainMenuFragment() {
        list=new ArrayList<>();
        categories=new ArrayList<>();
        populateList();
        populateCategoty();

    }

    private void populateCategoty() {
        Category cat1=new Category(picUrl,"Italian",1);
        Category cat2=new Category(picUrl2,"Continental",2);
        for (int i = 0; i <6 ; i++) {
            categories.add(cat1);
            categories.add(cat2);
        }
    }

    private void populateList() {
        Product product=new Product("La Tomati Pizza",
                "ghjfgjfgfg",picUrl,"Italian",1,65);
        Product p2=new Product("Cheese Pizza","gjhgfggfg",
                picUrl2,"Continental",1,89);
        for (int i = 0; i <6 ; i++) {
            list.add(product);
            list.add(p2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main_menu, container, false);
        ViewPager pager=view.findViewById(R.id.slider_viewpager);
        TrendingSliderAdapter adapter=new TrendingSliderAdapter(getContext(),list);
        pager.setAdapter(adapter);
        pager.setPadding(0,10,0,10);
        RecyclerView r=view.findViewById(R.id.category_recycler);
        RecyclerView foodrecycler=view.findViewById(R.id.foodmenu_recycler);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        foodrecycler.setLayoutManager(layoutManager);
        FoodMenuAdapter adapter1=new FoodMenuAdapter(getContext(),list);
        adapter1.setOnClickListener(position -> {
            if (list!=null){
                Product product=list.get(position);
                Intent intent=new Intent(getContext(), DetailedFoodActivity.class);
                intent.putExtra("detailed_product",product);
                startActivity(intent);
            }
        });
        foodrecycler.setAdapter(adapter1);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        r.setLayoutManager(manager);
        r.setAdapter(new CategoryRecyclerAdapter(getContext(),categories));
        return view;
    }

}
