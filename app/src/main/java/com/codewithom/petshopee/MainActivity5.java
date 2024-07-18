package com.codewithom.petshopee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity5 extends AppCompatActivity {

    TextView t7;
    Button b1,b2;
   EditText memail;
    String stremail;
    ProgressBar pb;
    FirebaseAuth mauth;
    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ConstraintLayout constraintLayout= findViewById(R.id.mainlayout5);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        pb = findViewById(R.id.progressBar2);
        b2 = findViewById(R.id.googlebtn);
        t7 = findViewById(R.id.blogin);
        memail = findViewById(R.id.memail);
        mauth = FirebaseAuth.getInstance();
       t7.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
               startActivity(intent);
           }
       });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stremail = memail.getText().toString().trim();
                if(!TextUtils.isEmpty(stremail)){
                   ResetPassword();
                }else{
                    memail.setError("email field cant be empty");
                }
            }
        });
    }

    private void ResetPassword(){
         pb.setVisibility(View.VISIBLE);
         b2.setVisibility(View.INVISIBLE);
         mauth.sendPasswordResetEmail(stremail).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                 Toast.makeText(MainActivity5.this, "Reset Password Link has been sent to your Regsitered Email", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
                 startActivity(intent);
                 finish();
             }
         })   .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(MainActivity5.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                 pb.setVisibility(View.INVISIBLE);
                 b2.setVisibility(View.VISIBLE);
             }
         });
    }
}