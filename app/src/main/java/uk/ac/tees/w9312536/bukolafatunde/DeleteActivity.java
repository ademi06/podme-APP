package uk.ac.tees.w9312536.bukolafatunde;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.w9312536.bukolafatunde.ui.detail.DetailActivity;

public class DeleteActivity extends AppCompatActivity {
    TextView DeleteAccount, etEmail;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        etEmail = findViewById(R.id.editTextEmail);
        DeleteAccount = findViewById(R.id.Delete_Settings);
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseuser = firebaseAuth.getCurrentUser();
//
//
//
//        etEmail.setText(firebaseuser,getemail());
        Intent in = getIntent();
        String string = in.getStringExtra("message");
        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("Deleting this account will result in completely removal of account from the system." +
                                "You sure, that you want to Delete Account?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText( DeleteActivity.this, "Account Deleted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else{
                                    Toast.makeText( DeleteActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }

                        });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                    }
                });


            }
        }

