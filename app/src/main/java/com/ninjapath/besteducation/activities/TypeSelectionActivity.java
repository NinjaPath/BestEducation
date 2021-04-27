package com.ninjapath.besteducation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ninjapath.besteducation.enums.AccountType;
import com.ninjapath.besteducation.R;

public class TypeSelectionActivity extends AppCompatActivity {

    Button studentButton;
    Button teacherButton;
    ImageButton backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);

        studentButton = findViewById(R.id.student_button);
        teacherButton = findViewById(R.id.teacher_button);
        backArrow = findViewById(R.id.backArrow);

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

        backArrow.setOnClickListener(view -> {
            Intent backIntent = new Intent(TypeSelectionActivity.this,GreetingActivity.class);
            startActivity(backIntent);
        });
    }
}
