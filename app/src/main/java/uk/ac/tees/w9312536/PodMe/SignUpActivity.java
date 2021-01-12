package uk.ac.tees.w9312536.PodMe;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import uk.ac.tees.w9312536.bukolafatunde.R;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    TextView login;
    EditText etName, etEmail, etPassword, etPasswordAgain;
    Button signUpButton;
    CheckBox mCheckBox;
    private ProgressBar progressBar;
    String sPassword, sConfirmPassword, sEmail, sName, userId, saveCurrentDate, saveCurrentTime;
    /* Constant for logging */
    private static final String TAG = SignUpActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_sign_up);
        etName = findViewById(R.id.editTextName);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etPasswordAgain = findViewById(R.id.editTextPasswordAgain);
        signUpButton = findViewById(R.id.signUpButton);
        login = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mCheckBox = findViewById(R.id.checkbox_terms);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* navigate to login activity */
                Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* validate edit text field and then proceed to sign up */

                register();
            }
        });

    }


    private void register() {
        progressBar.setVisibility(View.VISIBLE);

        if (etEmail.getText().length() == 0) {
            progressBar.setVisibility(View.GONE);
            etEmail.setError("Please fill this field");
            return;
        }
        if (etName.getText().length() == 0) {
            progressBar.setVisibility(View.GONE);
            etName.setError("Please fill this field");
            return;
        }
        if (etPassword.getText().length() == 0) {
            progressBar.setVisibility(View.GONE);
            etPassword.setError("Please fill this field");
            return;
        }
        if (etPassword.getText().length() < 6) {
            progressBar.setVisibility(View.GONE);
            etPassword.setError("Password must have at least 6 characters");
            return;
        }


        /* hide keyboard layout */
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        assert inputManager != null;
        inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        signUpButton.setEnabled(false);


        if (checkPasswordNotMatch()) {
            checkBoxClicked();

        } else {
            /* passwords are different, show toast with error message */
            progressBar.setVisibility(View.GONE);
            signUpButton.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }

    }

    private void registerUser(String sEmail, String sPassword) {
        /* check if email contain necessary details */
        mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            /* Get and save the Uid to string for later upload */
                            currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            assert currentUser != null;
                            userId = currentUser.getUid();

                            /* upload details to fire store */

                            upload();

                        } else {
                            signUpButton.setEnabled(true);
                            Toast.makeText(getApplicationContext(), "Registration failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

    private void upload() {
        /* Get current date */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat CurrentDate = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());
        saveCurrentDate = CurrentDate.format(calendar.getTime());

        /* Get current time */
        Calendar time = Calendar.getInstance();
        SimpleDateFormat CurrentTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
        saveCurrentTime = CurrentTime.format(time.getTime());

        DocumentReference docRef = db.collection("Users").document(userId);
        Map<String, Object> users = new HashMap<>();
        users.put("name", sName);
        users.put("email", sEmail);
        users.put("date_of_creation", saveCurrentDate);
        users.put("time_of_creation", saveCurrentTime);
        users.put("username", "");
        users.put("bio", "");
        users.put("image", "");

        /* add user to database */
        docRef.set(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        /* send user to main activity when registration is complete */
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onSuccess: Created User ");

                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       progressBar.setVisibility(View.GONE);
                       Toast.makeText(getApplicationContext(), "Error! Failed to Create User", Toast.LENGTH_SHORT).show();
                       Log.d(TAG, "onFailure: Failed to Create User " + e.toString());
                   }
               });


    }

    /* check password/pin for match */
    private boolean checkPasswordNotMatch() {
        /* get values of edit text to save in string temporarily */
        sPassword = etPassword.getText().toString();
        sConfirmPassword = etPasswordAgain.getText().toString();

        /* check for password if matched when entered twice */
        if (!sPassword.equals(sConfirmPassword)) {
            etPasswordAgain.setError(getString(R.string.err_msg_password_confirm));
            etPassword.setError(getString(R.string.err_msg_password_confirm));
            return false;
        }

        return true;
    }

    /* check box terms */

    private void checkBoxClicked() {
        // Is the view now checked?
            if ( mCheckBox.isChecked()) {
                sEmail = etEmail.getText().toString();
                sPassword = etPassword.getText().toString();
                sName = etName.getText().toString();

                registerUser(sEmail, sPassword);
            } else {
                progressBar.setVisibility(View.GONE);
                signUpButton.setEnabled(true);
                Toast.makeText(getApplicationContext(), "You need to agree to the terms and conditions to use the app", Toast.LENGTH_SHORT).show();
            }

    }
}
























