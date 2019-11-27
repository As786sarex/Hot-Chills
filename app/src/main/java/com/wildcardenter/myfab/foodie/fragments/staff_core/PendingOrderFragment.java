package com.wildcardenter.myfab.foodie.fragments.staff_core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.model.Document;
import com.wildcardenter.myfab.foodie.R;
import com.wildcardenter.myfab.foodie.adapters.OrderAdapter;
import com.wildcardenter.myfab.foodie.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PendingOrderFragment extends Fragment {

    private OrderAdapter adapter;
    private List<Order> orderList;
    private ListenerRegistration registration;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pending_order, container, false);
        orderList=new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.pending_order_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new OrderAdapter(getContext());
        loadAllOrders();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void loadAllOrders() {
        registration=FirebaseFirestore.getInstance().collection("Orders")
                .whereEqualTo("pending",true)
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {
                            orderList.clear();
                            if (queryDocumentSnapshots != null) {
                                for (DocumentSnapshot document:queryDocumentSnapshots.getDocuments()){
                                    Order order=document.toObject(Order.class);
                                    orderList.add(order);
                                }
                                adapter.setOrders(orderList);
                                adapter.notifyDataSetChanged();
                            }

                        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        registration.remove();
    }
}
