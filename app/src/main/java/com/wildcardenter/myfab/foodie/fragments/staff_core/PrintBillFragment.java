package com.wildcardenter.myfab.foodie.fragments.staff_core;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildcardenter.myfab.foodie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrintBillFragment extends Fragment {


    public PrintBillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_print_bill, container, false);
    }

}