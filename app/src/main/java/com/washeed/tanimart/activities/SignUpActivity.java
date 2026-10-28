package com.washeed.tanimart.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.washeed.tanimart.R;
import com.washeed.tanimart.data.AppDatabase;
import com.washeed.tanimart.data.entities.User;
import com.washeed.tanimart.data.repository.UserRepository;
import com.washeed.tanimart.utils.SimpleTextWatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPassword;
    private TextInputEditText signUpName, signUpEmail, signUpPassword, signUpLocation;
    private Button signUpRegisterButton;
    ExecutorService executor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        signUpName = findViewById(R.id.signUpName);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpLocation = findViewById(R.id.signUpLocation);
        signUpRegisterButton = findViewById(R.id.signUpRegisterButton);
        UserRepository userRepository = new UserRepository(AppDatabase.getDatabase(this).userDao());

        signUpRegisterButton.setOnClickListener(x -> {
            if (!validateInputs()) {return;}
            String name = signUpName.getText().toString().trim();
            String email = signUpEmail.getText().toString().trim();
            String password = signUpPassword.getText().toString().trim();
            String location = signUpLocation.getText().toString().trim();

            executor.execute(() -> {
                User existingUser = userRepository.getUserByEmail(email);
                if (existingUser != null) {
                    runOnUiThread(() -> tilEmail.setError("Email already registered"));
                    return;
                }
                User newUser = new User(name, email, password, 0, location.isEmpty() ? null : location);
                userRepository.userInsert(newUser);
                runOnUiThread(() -> {
                    Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                    finish();
                });
            });

        });
        signUpName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {tilName.setError(null);}
        });
        signUpEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {tilEmail.setError(null);}
        });
        signUpPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {tilPassword.setError(null);}
        });



    }  //end of onCreate

    private boolean validateInputs() {
        boolean isValid = true;
        String name = signUpName.getText().toString().trim();
        String email = signUpEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        // Name Validation
        if (name.isEmpty()) {
            tilName.setError("Name is required");
            isValid = false;
        } else {
            tilName.setError(null);
        }
        // Email Validation
        if (email.isEmpty()) {
            tilEmail.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Enter a valid email");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }
        // Password Validation
        if (password.isEmpty()) {
            tilPassword.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }
        // Location is OPTIONAL
        return isValid;
    }

}