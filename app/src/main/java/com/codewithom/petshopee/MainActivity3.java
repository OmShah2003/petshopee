package com.codewithom.petshopee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signup;
    TextView name, email, dogi,cati,parroti,adoptioni;
    ImageView dogs,cat,parrot,adoption;
    DrawerLayout drawerlayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        // signup = findViewById(R.id.button3);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);


        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);

        if(acc!=null){
            String personname = acc.getDisplayName();
            String personemail = acc.getEmail();
            // name.setText(personname);
            //email.setText(personemail);
        }
     /* signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });*/

        drawerlayout = findViewById(R.id.drawerlayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationview = findViewById(R.id.navigationview);
        dogs = findViewById(R.id.dogs);
        dogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, dog.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        dogi = findViewById(R.id.dogi);
        cat = findViewById(R.id.cat);
        parrot = findViewById(R.id.parrot);
        adoption = findViewById(R.id.adoption);
        cati = findViewById(R.id.cati);
        parroti = findViewById(R.id.parroti);
        adoptioni = findViewById(R.id.adoptioni);
        dogi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, dog.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, cat.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        parrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, parrot.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        adoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, adoption.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        cati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, cat.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        parroti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, parrot.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
        adoptioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, adoption.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });





        // ********** button *********
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.open();
            }
        });

        View headerView = navigationview.getHeaderView(0);
        ImageView userimage = headerView.findViewById(R.id.UserImage);
        TextView textUserName = headerView.findViewById(R.id.textUserName);

        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, textUserName.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navmenu) {

                    Toast.makeText(MainActivity3.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navCart) {
                    Intent intent = new Intent(MainActivity3.this, cart.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity3.this, "Cart Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navFavourite) {
                    Toast.makeText(MainActivity3.this, "Favourite Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navOrder) {
                    Toast.makeText(MainActivity3.this, "Order Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navHistory) {
                    Toast.makeText(MainActivity3.this, "History Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navFeedback) {
                    Toast.makeText(MainActivity3.this, "Feedback Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navTerms) {
                    Toast.makeText(MainActivity3.this, "Terms Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navContact) {
                    Toast.makeText(MainActivity3.this, "Contact Clicked", Toast.LENGTH_SHORT).show();
                }

                if (itemId == R.id.navShare) {
                    Toast.makeText(MainActivity3.this, "Share Clicked", Toast.LENGTH_SHORT).show();
                }
                drawerlayout.close();
                return false;
            }
        });
    }

    void signUp(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
    }
}
