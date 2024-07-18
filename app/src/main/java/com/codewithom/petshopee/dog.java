package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dog extends AppCompatActivity {
    View labra, germandog, husky, rottweiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        labra = findViewById(R.id.labradog);
        germandog = findViewById(R.id.germandog);
        husky = findViewById(R.id.husky);
        rottweiler = findViewById(R.id.rottweiler);

        labra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dog.this, labra_dog.class);
                startActivity(intent);
                Toast.makeText(dog.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        germandog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dog.this, germanshephard.class);
                startActivity(intent);
                Toast.makeText(dog.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        husky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dog.this, husky.class);
                startActivity(intent);
            }
        });

        rottweiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dog.this, rottweiler.class);
                startActivity(intent);
            }
        });
    }
}
