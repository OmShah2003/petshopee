package com.codewithom.petshopee;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity2 extends AppCompatActivity {
    //View layout = findViewById(R.id.mainlayout2);
    ImageView img;
    TextView t1,t2,t4,t5;
    EditText mpassword,memail;
    Button b2;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView google_btn;
    FirebaseAuth auth;
    ProgressBar pb;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img = findViewById(R.id.imageView2);
        t1= findViewById(R.id.textView);
        t2 = findViewById(R.id.textView3);
        pb = findViewById(R.id.progressBar2);
        t4 = findViewById(R.id.textView4);
        t5 = findViewById(R.id.fpassword);
        memail = findViewById(R.id.editTextText2);
        mpassword = findViewById(R.id.editTextTextPassword2);
        auth = FirebaseAuth.getInstance();
        ConstraintLayout constraintLayout= findViewById(R.id.mainlayout2);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        b2= findViewById(R.id.button2);
       // b2.setBackgroundResource(R.color.orange);
      /*  b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity2.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        }); */

        b2.setOnClickListener(new View.OnClickListener() {
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

                // authenticate the user
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(MainActivity2.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity3.class));

                        }else{
                            Toast.makeText(MainActivity2.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
            }

        });

        google_btn = findViewById(R.id.google_icon);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIN();
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity_register4.class);
                startActivity(intent);
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(intent);
            }
        });
    }

    void signIN(){
        Intent signinIntent = gsc.getSignInIntent();
        startActivityForResult(signinIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToMainActivity3();
            } catch (ApiException e) {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToMainActivity3(){
        finish();
          Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
          startActivity(intent);


    }


}