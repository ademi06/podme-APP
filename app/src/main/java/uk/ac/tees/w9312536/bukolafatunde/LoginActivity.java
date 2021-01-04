package uk.ac.tees.w9312536.bukolafatunde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView forgotPassword, signUp;
    EditText etEmail, etPassword;
    Button loginButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotPassword = findViewById(R.id.tvForgotPassword);
        signUp = findViewById(R.id.tvSignUp);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* navigate to sign up activity */
                Intent signUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUp);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* navigate to forgot password activity */
                Intent signUp = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(signUp);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        /* validate that email and password fields are not empty else show error */
        if (etEmail.getText().length() == 0) {
            progressBar.setVisibility(View.GONE);
            etEmail.setError("Please enter email");
            return;
        }
        if (etPassword.getText().length() == 0) {
            progressBar.setVisibility(View.GONE);
            etPassword.setError("Please enter password");
            return;
        }
        loginButton.setClickable(false);
        /* get the email and password to string for temporary storage in the activity */
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        /* hide keyboard layout */
//        InputMethodManager inputManager = (InputMethodManager)
//                getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        assert inputManager != null;
//        inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            /* navigate to main activity if email and password matches what is in the database */
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            loginButton.setClickable(true);
                            Toast.makeText(LoginActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}