package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.enums.AccountType;

public class GreetingActivity extends AppCompatActivity {

    private static final String TAG = "GreetingActivity";
    private Button signIn;
    private Button logIn;
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore ;


    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            fstore = FirebaseFirestore.getInstance();
            Intent intentToHome = new Intent(GreetingActivity.this, MainActivity.class);
            intentToHome.putExtra(MainActivity.ACCOUNT_TYPE, AccountType.TEACHER);
            startActivity(intentToHome);
//            fstore.collection("users").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot document = task.getResult();
//                        Intent intentToHome = new Intent(GreetingActivity.this, MainActivity.class);
//                        intentToHome.putExtra(MainActivity.ACCOUNT_TYPE, AccountType.valueOf(
//                                document.getString("accountType").toUpperCase()));
//                        startActivity(intentToHome);
//                    }
//                }
//            });

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
