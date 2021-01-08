package uk.ac.tees.w9312536.bukolafatunde;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogOutActivity extends AppCompatActivity {
    TextView LogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        LogOut = findViewById(R.id.LogOut_Settings);

            Intent in = getIntent();
            String string = in.getStringExtra("message");
            LogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogOutActivity .this);
                    builder.setTitle("Confirmation PopUp!").
                            setMessage("You sure, that you want to logout?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(getApplicationContext(),
                                            MainActivity.class);
                                    startActivity(i);
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }
            });
        }
    }