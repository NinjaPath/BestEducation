package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ninjapath.besteducation.AuthenticationData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.enums.AccountType;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.AuthenticationDataValidation;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    public static final String ACCOUNT_TYPE = "accountType";
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private ImageButton arrowBack;
    private TextView singInText;
    private Button singInButton;
    private EditText nicknameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatedPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        String accountType = getIntent().getExtras().getString(ACCOUNT_TYPE);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        arrowBack = findViewById(R.id.backArrow);
        singInText = findViewById(R.id.sign_in);
        singInButton = findViewById(R.id.sign_in_button);
        nicknameEditText = findViewById(R.id.nickname_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        repeatedPasswordEditText = findViewById(R.id.repeat_password_edit_text);
        singInText = findViewById(R.id.sign_in_text_view);
        singInButton.setOnClickListener(view -> {
            AuthenticationData authenticationData = new AuthenticationData(accountType, nicknameEditText.getText().toString(),
                    emailEditText.getText().toString(), passwordEditText.getText().toString(),
                    repeatedPasswordEditText.getText().toString());
            try {
                AuthenticationDataValidation.validateData(authenticationData);
                mAuth.createUserWithEmailAndPassword(authenticationData.getEmail(),
                        authenticationData.getPassword())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Map<String, Object> userInfo = new HashMap<>();
                                userInfo.put("id", mAuth.getCurrentUser().getUid());
                                userInfo.put("accountType", authenticationData.getAccountType());
                                userInfo.put("nickname", authenticationData.getUsername());
                                userInfo.put("email", authenticationData.getEmail());
                                userInfo.put("password", authenticationData.getPassword());
                                fstore.collection("users").
                                        document(mAuth.getCurrentUser().getUid()).set(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intentToMain = new Intent(RegistrationActivity.this, MainActivity.class);
                                            intentToMain.putExtra(MainActivity.ACCOUNT_TYPE, accountType);
                                            startActivity(intentToMain);
                                        }
                                    }
                                });
                            }
                        });
            } catch (EntryException e) {
                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
            }
        });
    }
}
