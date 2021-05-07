package com.ninjapath.besteducation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ninjapath.besteducation.AuthenticationData;
import com.ninjapath.besteducation.LoginData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.enums.AccountType;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.AuthenticationDataValidation;

import java.util.HashMap;
import java.util.Map;


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
                                for (QueryDocumentSnapshot document : task1.getResult()){
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
//                mAuth.createUserWithEmailAndPassword(authenticationData.getEmail(),
//                        authenticationData.getPassword())
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                Map<String, Object> userInfo = new HashMap<>();
//                                userInfo.put("id", mAuth.getCurrentUser().getUid());
////                                userInfo.put("accountType", authenticationData.getAccountType());
//                                userInfo.put("email", authenticationData.getEmail());
//                                userInfo.put("password", authenticationData.getPassword());
//                                fstore.collection("users").
//                                        document(mAuth.getCurrentUser().getUid()).set(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Intent intentToMain = new Intent(LoginActivity.this, MainActivity.class);
////                                            intentToMain.putExtra(MainActivity.ACCOUNT_TYPE, accountType);
//                                            startActivity(intentToMain);
//                                        } else {
//                                            SnackbarMessages.makeSnackbarError(view, getResources().getString(R.string.unexpected_error));
//                                        }
//                                    }
//                                });
//                            }
//                        });
            } catch (EntryException e) {
                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
            }

        });
    }}
