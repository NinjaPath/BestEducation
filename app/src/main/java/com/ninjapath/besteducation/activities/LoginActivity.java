package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ninjapath.besteducation.LoginData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.enums.AccountType;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.AuthenticationDataValidation;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private ImageButton arrowBack;
    private FirebaseAuth mAuth;
    private Button logIn;
    private Button signIn;
    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logIn = findViewById(R.id.sign_in_button);
        signIn = findViewById(R.id.sign_in_tool);
        arrowBack = findViewById(R.id.backArrow);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        arrowBack.setOnClickListener(view -> {
            Intent backIntent = new Intent(LoginActivity.this, GreetingActivity.class);
            startActivity(backIntent);
        });

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, TypeSelectionActivity.class);
            startActivity(intent);
        });

        logIn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        });

        logIn.setOnClickListener(view -> {
            LoginData loginData = new LoginData(emailEditText.getText().toString(), passwordEditText.getText().toString());
//
            try {
                AuthenticationDataValidation.validateData(loginData);
                mAuth.signInWithEmailAndPassword(loginData.getEmail(), loginData.getPassword()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "invalid data");
                        fstore.collection("users").whereEqualTo("id", mAuth.getCurrentUser().getUid())
                                .get().addOnCompleteListener(task1 -> {
                            for (QueryDocumentSnapshot document : task1.getResult()) {
                                String accountType = document.getString("accountType");
                                AccountType enumAccount = AccountType.valueOf(accountType.toUpperCase());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(MainActivity.ACCOUNT_TYPE, enumAccount);
                                startActivity(intent);
                            }
                        });
                    } else {
                        SnackbarMessages.makeSnackbarError(view, "Пользователя не существует");
                    }
                });
            } catch (EntryException e) {
                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
            }

        });
    }
}
