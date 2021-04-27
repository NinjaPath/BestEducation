package com.ninjapath.besteducation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.auth.viewmodel.AuthViewModelBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ninjapath.besteducation.R;

public class GreetingActivity extends AppCompatActivity {

    private Button signIn;
    private Button logIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intentToHome = new Intent(GreetingActivity.this,
                    MainActivity.class);
            startActivity(intentToHome);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        signIn = findViewById(R.id.sign_in);
        logIn = findViewById(R.id.log_in);

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(GreetingActivity.this, TypeSelectionActivity.class);
            startActivity(intent);
        });

        logIn.setOnClickListener(v -> {
            Intent intent = new Intent(GreetingActivity.this, LoginActivity.class);
            startActivity(intent);
        });



    }
}
