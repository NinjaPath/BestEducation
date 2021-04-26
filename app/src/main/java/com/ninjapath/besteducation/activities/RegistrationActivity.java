package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ninjapath.besteducation.AuthenticationData;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.SnackbarMessages;
import com.ninjapath.besteducation.exceptions.EntryException;
import com.ninjapath.besteducation.validationClasses.AuthenticationDataValidation;

public class RegistrationActivity extends AppCompatActivity {

    public static final String ACCOUNT_TYPE = "accountType";
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
        arrowBack = findViewById(R.id.backArrow);
        singInText = findViewById(R.id.sign_in);
        singInButton = findViewById(R.id.sign_in_button);
        nicknameEditText = findViewById(R.id.nickname_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        repeatedPasswordEditText = findViewById(R.id.repeat_password_edit_text);
        singInText = findViewById(R.id.sign_in_text_view);
        arrowBack.setOnClickListener(view -> {
            Intent backIntent = new Intent(RegistrationActivity.this, GreetingActivity.class);
            startActivity(backIntent);
        });
        singInText.setOnClickListener(view -> {
            Intent intentToLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intentToLogin);
        });
        singInButton.setOnClickListener(view -> {
            AuthenticationData authenticationData = new AuthenticationData(nicknameEditText.getText().toString(),
                    emailEditText.getText().toString(), passwordEditText.getText().toString(),
                    repeatedPasswordEditText.getText().toString());
            try {
                AuthenticationDataValidation.validateData(authenticationData);
            } catch (EntryException e) {
                SnackbarMessages.makeSnackbarError(view, e.getErrorCode().getErrorString());
            }
        });
    }
}
