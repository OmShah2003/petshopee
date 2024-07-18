package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class cat extends AppCompatActivity {
    View persian,bombay_cat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        persian = findViewById(R.id.persian_cat);
        bombay_cat= findViewById(R.id.bombay_cat);


        persian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cat.this, persian_cat.class);
                startActivity(intent);
                Toast.makeText(cat.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        bombay_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cat.this, bombay_cat.class);
                startActivity(intent);
                Toast.makeText(cat.this, "success", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
