package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ninjapath.besteducation.R;

public class GreetingActivity extends AppCompatActivity {

    private Button signIn;
    private Button logIn;

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
