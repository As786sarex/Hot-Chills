package com.wildcardenter.myfab.foodie.fragments.staff_core;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildcardenter.myfab.foodie.R;
import com.google.android.gms.plus.PlusOneButton;
import com.wildcardenter.myfab.foodie.activities.AddItemActivity;
import com.wildcardenter.myfab.foodie.activities.StaffLandingActivity;
import com.wildcardenter.myfab.foodie.adapters.FavouriteAdapter;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

public class AllProductsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_products, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.staff_all_product_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        FavouriteAdapter adapter=new FavouriteAdapter(getContext());
        MainMenuViewModel viewModel= ViewModelProviders.of(this)
                .get(MainMenuViewModel.class);
        viewModel.getAllProductsFromDb().observe(this,list->{
            adapter.setProducts(list);
            adapter.notifyDataSetChanged();
        });
        recyclerView.setAdapter(adapter);
        view.findViewById(R.id.staff_add_item).setOnClickListener(o->{
            startActivity(new Intent(getContext(), AddItemActivity.class));
        });
        return view;
    }

}
