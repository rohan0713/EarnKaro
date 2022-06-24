package com.example.earn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class home_screen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_screen.this, linkActivity.class);
                startActivity(intent);
            }
        });


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                if(item.getItemId() == R.id.home){
                    fragment = new HomeFragment();
                }
                else if(item.getItemId() == R.id.task){
                    fragment = new PartnerFragment();
                }else if(item.getItemId() == R.id.activity){
                    fragment = new ActivityFragment();
                }else if(item.getItemId() == R.id.profile){
                    fragment = new ProfileFragment();
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment)
                        .commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(bottomNavigationView.getSelectedItemId() == R.id.home) {
            super.onBackPressed();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }
}