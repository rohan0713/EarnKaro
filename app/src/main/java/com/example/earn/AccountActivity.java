package com.example.earn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

public class AccountActivity extends AppCompatActivity {

    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5, textInputLayout6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ImageView back = findViewById(R.id.profile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textInputLayout1 = findViewById(R.id.textinputlayout1);
        textInputLayout2 = findViewById(R.id.textinputlayout2);
        textInputLayout3 = findViewById(R.id.textinputlayout3);
        textInputLayout4 = findViewById(R.id.textinputlayout4);
        textInputLayout5 = findViewById(R.id.textinputlayout5);
        textInputLayout6 = findViewById(R.id.textinputlayout6);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    textInputLayout1.setVisibility(View.VISIBLE);
                    textInputLayout2.setVisibility(View.VISIBLE);
                    textInputLayout3.setVisibility(View.VISIBLE);
                    textInputLayout4.setVisibility(View.GONE);
                    textInputLayout5.setVisibility(View.GONE);
                    textInputLayout6.setVisibility(View.GONE);
                }else{
                    textInputLayout1.setVisibility(View.GONE);
                    textInputLayout2.setVisibility(View.GONE);
                    textInputLayout3.setVisibility(View.GONE);
                    textInputLayout4.setVisibility(View.VISIBLE);
                    textInputLayout5.setVisibility(View.VISIBLE);
                    textInputLayout6.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}