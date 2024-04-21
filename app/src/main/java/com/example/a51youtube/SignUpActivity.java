package com.example.a51youtube;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private TextView fullnamePlainText;
    private TextView usernameSignUpPlainText;
    private TextView passwordSignUpPlainText;
    private TextView confirmPasswordPlainText;
    private Button createAccountButton;
    private UserDbHandler userDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Initialize views
        fullnamePlainText = findViewById(R.id.fullnamePlainText);
        usernameSignUpPlainText = findViewById(R.id.usernameSignUpPlainText);
        passwordSignUpPlainText = findViewById(R.id.paswordSignUpPlainText);
        confirmPasswordPlainText = findViewById(R.id.confirmPasswordPlainText);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Create Account Button Click Listener
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullnamePlainText.getText().toString();
                String username = usernameSignUpPlainText.getText().toString();
                String password = passwordSignUpPlainText.getText().toString();
                String confirmPassword = confirmPasswordPlainText.getText().toString();

                // Validation checks
                if (name.isEmpty()) {
                    fullnamePlainText.setError("Full name is required");
                    fullnamePlainText.requestFocus();
                    return;
                }

                if (username.isEmpty()) {
                    usernameSignUpPlainText.setError("Username is required");
                    usernameSignUpPlainText.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordSignUpPlainText.setError("Password is required");
                    passwordSignUpPlainText.requestFocus();
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    confirmPasswordPlainText.setError("Please confirm your password");
                    confirmPasswordPlainText.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    confirmPasswordPlainText.setError("Passwords do not match");
                    confirmPasswordPlainText.requestFocus();
                    return;
                }

                // Create new user
                USER user = USER.getInstance(name, username, password);
                userDbHandler = new UserDbHandler(SignUpActivity.this);
                userDbHandler.addNewUSER(user);

                Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
