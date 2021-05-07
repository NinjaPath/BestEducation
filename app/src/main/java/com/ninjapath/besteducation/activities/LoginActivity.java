package com.ninjapath.besteducation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ninjapath.besteducation.AuthenticationData;
import com.ninjapath.besteducation.LoginData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.AuthenticationDataValidation;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    public static final String ACCOUNT_TYPE = "accountType";
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
//        String accountType = getIntent().getExtras().getString(ACCOUNT_TYPE);

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

//        logIn.setOnClickListener(view -> {
//            LoginData authenticationData = new LoginData(accountType, emailEditText.getText().toString(), passwordEditText.getText().toString());

//            try {
//                AuthenticationDataValidation.validateData(authenticationData);
//                mAuth.createUserWithEmailAndPassword(authenticationData.getEmail(),
//                        authenticationData.getPassword())
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                Map<String, Object> userInfo = new HashMap<>();
//                                userInfo.put("id", mAuth.getCurrentUser().getUid());
//                                userInfo.put("accountType", authenticationData.getAccountType());
//                                userInfo.put("email", authenticationData.getEmail());
//                                userInfo.put("password", authenticationData.getPassword());
//                                fstore.collection("users").
//                                        document(mAuth.getCurrentUser().getUid()).set(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Intent intentToMain = new Intent(LoginActivity.this, MainActivity.class);
//                                            intentToMain.putExtra(MainActivity.ACCOUNT_TYPE, accountType);
//                                            startActivity(intentToMain);
//                                        } else {
//                                            SnackbarMessages.makeSnackbarError(view, getResources().getString(R.string.unexpected_error));
//                                        }
//                                    }
//                                });
//                            }
//                        });
//            } catch (EntryException e) {
//                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
//            }

//        });
    }}
