package com.example.earn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class otpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        MaterialButton verify = findViewById(R.id.verificationBtn);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelperClass.put(otpActivity.this, "login", "true");
                Intent intent = new Intent(view.getContext(), home_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}