package com.codewithom.petshopee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class germanshephard extends AppCompatActivity {
    ImageView addition, subtract;
    TextView counting;

    public int amount = 0,price = 1;
    int totalQuantity =1;
    Button addtocart;
    LinearLayout labradog;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_germanshephard);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        addition = findViewById(R.id.add);
        subtract = findViewById(R.id.minus);
        counting = findViewById(R.id.count);
        addtocart= findViewById(R.id.cart);
        labradog= findViewById(R.id.german_dog);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedtocart();
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity<10){
                    totalQuantity++;
                    amount = totalQuantity*price;
                    counting.setText(String.valueOf(totalQuantity));
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity>1){
                    totalQuantity --;
                    amount = totalQuantity*price;
                    counting.setText(String.valueOf(totalQuantity));
                    //Toast.makeText(rottweiler.this, "Amount: "+amount, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addedtocart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("totalquantity",totalQuantity);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("product","german");
        cartMap.put("amount",amount);

//        firestore.collection("Petshopee").document(auth.getCurrentUser().getUid()).collection("CurrentUsesr").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentReference> task) {
//                Toast.makeText(rottweiler.this, "Added to Cart", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
        firestore.collection("Petshopee")
                .document(auth.getCurrentUser().getUid())
                .collection("CurrentUsers")
                .document("wwgUTo5BXnD4J2ZARQae") // Specify your custom document ID here
                .set(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(germanshephard.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(germanshephard.this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    }
