package com.washeed.tanimart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;

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
import com.washeed.tanimart.main.MainActivity;
import com.washeed.tanimart.utils.SessionManager;
import com.washeed.tanimart.utils.SimpleTextWatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import com.washeed.tanimart.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText loginEmail, loginPassword;
    private TextView signUpButton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginEmail.setText("red@gmail.com");
        loginPassword.setText("123456");   // test mode easy login
        signUpButton = findViewById(R.id.signUpButton);
        loginButton=findViewById(R.id.loginButton);
        UserRepository userRepository = new UserRepository(AppDatabase.getDatabase(this).userDao());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        loginButton.setOnClickListener(v -> {
            if (!validateInputs()) {return;}

            String email = loginEmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();
            //match the email and password with the existing data from the database
            executor.execute(() -> {
                User user = userRepository.getUserByEmail(email);
                runOnUiThread(() -> {
                    if (user == null) {
                        tilEmail.setError("Email not found");
                        return;
                    }
                    if (!user.getUserPass().equals(password)) {
                        tilPassword.setError("Incorrect password");
                        return;
                    }
                    //the usersid is being saved on the session manager
                    SessionManager userSession = new SessionManager(this);
                    userSession.saveUserID(user.getUserID());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                });
            });
        });
        //---------------Goes to signup page-----------------
        signUpButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
        //if error is shown and the field is used again remove error message
        loginEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {tilEmail.setError(null);}
        });
        loginPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {tilPassword.setError(null);}
        });
    }

    //validates input and gives error message
    private boolean validateInputs() {
        boolean isValid = true;
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
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
        return isValid;
    }
}