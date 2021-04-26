package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ninjapath.besteducation.R;

public class RegistrationActivity extends AppCompatActivity {

    public static final String ACCOUNT_TYPE = "accountType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

    }
}
