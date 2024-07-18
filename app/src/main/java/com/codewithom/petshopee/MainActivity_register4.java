package com.codewithom.petshopee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_register4 extends AppCompatActivity {
   TextView t5;
    EditText fullname , memail,mpassword, mphone;
    Button register;
    FirebaseAuth auth;
    ProgressBar pb;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register4);
        t5 = findViewById(R.id.loggedin);
        fullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        register = findViewById(R.id.register);
        mphone = findViewById(R.id.phone);
        pb = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        // if user is already register
        if(auth.getCurrentUser()!=null){
            Toast.makeText(this, "user already register", Toast.LENGTH_SHORT).show();

        }

       t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_register4.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    mpassword.setError("Password must be more than 6 characters");
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                //register user with email && password
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity_register4.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                        }else{
                            Toast.makeText(MainActivity_register4.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}