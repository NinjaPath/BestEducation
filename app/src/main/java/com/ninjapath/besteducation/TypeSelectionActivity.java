package com.ninjapath.besteducation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TypeSelectionActivity extends AppCompatActivity {

    Button studentButton;
    Button teacherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);

        studentButton = findViewById(R.id.student_button);
        teacherButton = findViewById(R.id.teacher_button);

        studentButton.setOnClickListener(v -> {
            Intent intent = new Intent(TypeSelectionActivity.this, RegistrationActivity.class);
            intent.putExtra(RegistrationActivity.ACCOUNT_TYPE, AccountType.STUDENT.toString());
            startActivity(intent);
        });

        teacherButton.setOnClickListener(v -> {
            Intent intent = new Intent(TypeSelectionActivity.this, RegistrationActivity.class);
            intent.putExtra(RegistrationActivity.ACCOUNT_TYPE, AccountType.TEACHER.toString());
            startActivity(intent);
        });

    }
}
