package com.ninjapath.besteducation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ninjapath.besteducation.fragments.ChatsFragment;
import com.ninjapath.besteducation.fragments.HomeFragment;
import com.ninjapath.besteducation.fragments.ProfileFragment;
import com.ninjapath.besteducation.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
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

            };


}