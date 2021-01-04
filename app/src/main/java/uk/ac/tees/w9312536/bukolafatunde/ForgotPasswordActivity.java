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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText etEmail;
    Button resetPasswordButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.editTextEmail);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        progressBar = findViewById(R.id.progressBar);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* reset password logic */
                progressBar.setVisibility(View.VISIBLE);

                if (etEmail.getText().length() == 0) {
                    progressBar.setVisibility(View.GONE);
                    etEmail.setError("Please enter email");
                    return;
                }

                /* hide keyboard layout */
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                /* check the email field is correct with email in the firebase and then send link to change email */
                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.sendPasswordResetEmail(etEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // do something when mail was sent successfully.
                                    Toast.makeText(ForgotPasswordActivity.this, "Password reset mail sent",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // ...
                                    Toast.makeText(ForgotPasswordActivity.this, "Error: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}