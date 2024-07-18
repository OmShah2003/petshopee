package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adoption extends AppCompatActivity {
    View zoe , bunny , bruno , zara;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);
        zoe = findViewById(R.id.zoe);
        bunny = findViewById(R.id.bunny);
        bruno = findViewById(R.id.bruno);
        zara = findViewById(R.id.zara);
        zoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adoption.this, Zoe.class);
                startActivity(intent);
            }
        });

        bunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adoption.this, Bunny.class);
                startActivity(intent);
            }
        });

        bruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adoption.this, Bruno.class);
                startActivity(intent);
            }
        });

        zara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adoption.this, Zara.class);
                startActivity(intent);
            }
        });
    }
}