package com.codewithom.petshopee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    ImageView iv;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.two, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cat2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.parrot2, ScaleTypes.FIT));


        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        iv = findViewById(R.id.imageView);
        iv.setBackgroundResource(R.drawable.petshop);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}