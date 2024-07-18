package com.codewithom.petshopee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import adapter.mycartAdapter;
import adapter.mycartAdapter1;

public class cart extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    mycartAdapter1 cartAdapter;
    TextView overTotalAmount;
    List<mycartAdapter> cartItemList;
    Button buynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        overTotalAmount = findViewById(R.id.textView7);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));

        cartItemList = new ArrayList<>();
        cartAdapter = new mycartAdapter1(this, cartItemList);
        recyclerView.setAdapter(cartAdapter);

        buynow = findViewById(R.id.buy_now);

        db.collection("Petshopee").document(auth.getCurrentUser().getUid()).collection("CurrentUsers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        mycartAdapter cartItem = documentSnapshot.toObject(mycartAdapter.class);
                        cartItem.setDocumentId(documentSnapshot.getId());
                        if (cartItem != null) {
                            cartItemList.add(cartItem);
                        }
                    }
                    cartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(cart.this, "Failed to load cart items: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalAmount = calculateTotalAmount();
                Intent intent = new Intent(cart.this, buy.class);
                intent.putExtra("totalAmount", totalAmount);
                startActivity(intent);
            }
        });
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        for (mycartAdapter item : cartItemList) {
            totalAmount += item.getTotalquantity() * item.getAmount();
        }
        return totalAmount;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount", 0);
            overTotalAmount.setText("Total Bill: " + totalBill);
        }
    };
}
