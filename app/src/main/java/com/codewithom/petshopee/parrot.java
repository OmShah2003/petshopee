package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class parrot extends AppCompatActivity {
    View native_green, macaws;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parrot);

        native_green = findViewById(R.id.parrot_macaws);
        macaws = findViewById(R.id.nativegreen);


        native_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parrot.this, native_green.class);
                startActivity(intent);
                Toast.makeText(parrot.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        macaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parrot.this, macows.class);
                startActivity(intent);
                Toast.makeText(parrot.this, "success", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
