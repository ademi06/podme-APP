package uk.ac.tees.w9312536.bukolafatunde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView login;
    EditText  etName, etEmail, etPassword;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_sign_up);
        etName = findViewById(R.id.editTextName);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        signUpButton = findViewById(R.id.signUpButton);
        login = findViewById(R.id.tvLogin);


        login.setOnClickListener(v -> {
            /* navigate to login activity */
            Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(login);
            finish();
        });
    }

}