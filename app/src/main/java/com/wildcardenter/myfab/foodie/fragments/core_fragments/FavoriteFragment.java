package com.wildcardenter.myfab.foodie.fragments.core_fragments;


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
import com.wildcardenter.myfab.foodie.activities.DetailedFoodActivity;
import com.wildcardenter.myfab.foodie.adapters.FavouriteAdapter;
import com.wildcardenter.myfab.foodie.models.Product;
import com.wildcardenter.myfab.foodie.viewmodels.MainMenuViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements FavouriteAdapter.FabClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Product> allFabs;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        MainMenuViewModel viewModel=ViewModelProviders.of(this).get(MainMenuViewModel.class);
        RecyclerView favRecycler=view.findViewById(R.id.favourite_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        favRecycler.setLayoutManager(manager);
        FavouriteAdapter adapter=new FavouriteAdapter(getContext());
        viewModel.getFavProduct().observe(this,list->{
            allFabs=list;
            adapter.setProducts(list);
            adapter.notifyDataSetChanged();
        });
        adapter.setOnFabClickListener(this);
        favRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onFabClick(int pos) {
        if (allFabs != null) {
            Product product = allFabs.get(pos);
            Intent intent = new Intent(getContext(), DetailedFoodActivity.class);
            intent.putExtra("detailed_product", product);
            startActivity(intent);
        }
    }
}
