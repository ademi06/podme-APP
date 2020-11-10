package uk.ac.tees.w9312536.bukolafatunde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView forgotPassword, signUp;
    EditText etEmail, etPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotPassword = findViewById(R.id.tvForgotPassword);
        signUp = findViewById(R.id.tvSignUp);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);

        signUp.setOnClickListener(v -> {
            /* navigate to sign up activity */
            Intent signUp = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(signUp);
        });

        forgotPassword.setOnClickListener(v -> {
            /* navigate to forgot password activity */
            Intent signUp = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(signUp);
        });
    }
}