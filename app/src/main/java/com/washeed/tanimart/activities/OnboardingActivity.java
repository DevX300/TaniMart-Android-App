package com.washeed.tanimart.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.washeed.tanimart.R;
import com.washeed.tanimart.main.MainActivity;

public class OnboardingActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        preferences = getSharedPreferences("TaniMartPrefs", MODE_PRIVATE);
        boolean isOnboardingCompleted = preferences.getBoolean("onboardingCompleted", false);
        if (isOnboardingCompleted) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        logIn = findViewById(R.id.onboarding_button);
        logIn.setOnClickListener(v -> {
            preferences = getSharedPreferences("TaniMartPrefs", MODE_PRIVATE);
            preferences.edit().putBoolean("onboardingCompleted", true).apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}