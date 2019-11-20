package com.wildcardenter.myfab.foodie.fragments.login;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.activities.CustomerLandingActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerLoginFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_customer_login, container, false);
        view.findViewById(R.id.floatingActionButton).setOnClickListener(v->{
            startActivity(new Intent(getContext(), CustomerLandingActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        });
        return view;
    }

}
