package com.wildcardenter.myfab.foodie.fragments.core_fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.adapters.CartAdapter;
import com.wildcardenter.myfab.foodie.models.Product;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartAdapter.OnItemIteractListener {

    private List<Product> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView recyclerView= view.findViewById(R.id.cart_recycler);
        MainMenuViewModel viewModel= ViewModelProviders.of(this).get(MainMenuViewModel.class);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        CartAdapter adapter=new CartAdapter(getContext());
        list=viewModel.dummyProducts();
        adapter.setProductList(list);

        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onInteract(int position, int what) {
        if (what==PLUS){

        }
        else if(what==MINUS){

        }
    }
}
