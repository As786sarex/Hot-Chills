package com.wildcardenter.myfab.foodie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.helpers.Constants;

import es.dmoral.toasty.Toasty;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(getResources().getColor(R.color.splashBackground));
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            FirebaseFirestore.getInstance().collection(Constants.STAFF_COLLECTION)
                    .document(auth.getCurrentUser().getUid()).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            if (task.getResult()!=null){
                                if (task.getResult().exists()){
                                    startActivity(new Intent(SplashActivity.this
                                            ,StaffLandingActivity.class));
                                    finish();
                                }
                                else{
                                    openCustomerActivity();
                                }
                            }
                            else{
                                openCustomerActivity();
                            }
                        }
                        else{
                            Toasty.error(getApplicationContext(),"Error has occurred",Toasty.LENGTH_SHORT,true).show();
                        }
                    });
        }
        else{
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }, 3000);
        }
    }
    void openCustomerActivity(){
        startActivity(new Intent(SplashActivity.this
                ,CustomerLandingActivity.class));
        finish();
    }
}
