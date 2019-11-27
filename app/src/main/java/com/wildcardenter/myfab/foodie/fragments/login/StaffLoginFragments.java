package com.wildcardenter.myfab.foodie.fragments.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.activities.CustomerLandingActivity;
import com.wildcardenter.myfab.foodie.activities.StaffLandingActivity;
import com.wildcardenter.myfab.foodie.helpers.Constants;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffLoginFragments extends Fragment {


    MaterialEditText email, password;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_login, container, false);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        email = view.findViewById(R.id.customer_login_email);
        password = view.findViewById(R.id.customer_login_password);
        view.findViewById(R.id.customer_sign_in_btn).setOnClickListener(i -> {
            String em = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            if (em.isEmpty()) {
                email.setError("Field Required!");
            } else if (pass.isEmpty()) {
                password.setError("Field Required!");
            } else {
                attemptLogin(em, pass);
            }
        });
        view.findViewById(R.id.customer_open_signup).setOnClickListener(i->{
            startActivity(new Intent(getContext(), StaffLandingActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        });
        return view;
    }

    private void attemptLogin(String em, String pass) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please wait");
        dialog.show();
        auth.signInWithEmailAndPassword(em, pass)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()),
                        task -> {
                            if (task.isSuccessful()) {
                                firestore.collection(Constants.STAFF_COLLECTION)
                                        .document(task.getResult().getUser().getUid())
                                        .get().addOnSuccessListener(documentSnapshot -> {
                                    if (documentSnapshot.exists()) {
                                        dialog.dismiss();
                                        Toasty.success(getContext(), "Login Successful",
                                                Toasty.LENGTH_SHORT, true).show();
                                        startActivity(new Intent(getContext(), StaffLandingActivity.class));
                                        getActivity().finish();
                                    } else {
                                        dialog.dismiss();
                                        Toasty.error(getContext(), "Something went wrong",
                                                Toasty.LENGTH_SHORT, true).show();
                                    }
                                }).addOnFailureListener(e -> {
                                    dialog.dismiss();
                                    Toasty.error(getContext(), "Something went wrong",
                                            Toasty.LENGTH_SHORT, true).show();
                                });
                            } else {
                                Toasty.error(getContext(), "Something went wrong",
                                        Toasty.LENGTH_SHORT, true).show();
                                dialog.dismiss();
                            }
                        });
    }

}
