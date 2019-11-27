package com.wildcardenter.myfab.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.fragments.login.StaffLoginFragments;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(getResources().getColor(R.color.loginStatusColor));
    }

    public void openCustomerLogin(View view) {
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnSuccessListener(authResult -> {
                    dialog.dismiss();
                    startActivity(new Intent(this,CustomerLandingActivity.class));
                   finish();
                })
                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toasty.error(this, Objects.requireNonNull(e.getMessage()),Toasty.LENGTH_SHORT
                            ,true).show();
                });

    }

    public void staffLogin(View view) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.login_container, new StaffLoginFragments()).commit();
    }
}
