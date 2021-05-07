package com.ninjapath.besteducation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ninjapath.besteducation.fragments.ChatsFragment;
import com.ninjapath.besteducation.fragments.HomeFragment;
import com.ninjapath.besteducation.fragments.ProfileFragment;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.enums.AccountType;
import com.ninjapath.besteducation.fragments.ChatsFragment;
import com.ninjapath.besteducation.fragments.HomeFragment;
import com.ninjapath.besteducation.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    public static final String ACCOUNT_TYPE = "accountType";
    AccountType accountType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountType = (AccountType) getIntent().getExtras().getSerializable(ACCOUNT_TYPE);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case  R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case  R.id.nav_chats:
                            selectedFragment = new ChatsFragment();
                            break;
                        case  R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;

                    }

                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;

                }
            };

}
