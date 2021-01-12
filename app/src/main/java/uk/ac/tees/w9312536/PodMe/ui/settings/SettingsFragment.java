package uk.ac.tees.w9312536.PodMe.ui.settings;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import uk.ac.tees.w9312536.PodMe.ForgotPasswordActivity;
import uk.ac.tees.w9312536.PodMe.LocationActivity;
import uk.ac.tees.w9312536.PodMe.LoginActivity;
import uk.ac.tees.w9312536.PodMe.MainActivity;
import uk.ac.tees.w9312536.PodMe.MapActivity;
import uk.ac.tees.w9312536.bukolafatunde.R;

public class SettingsFragment extends Fragment {

    View view;
    Button mLocation, mLogout, mDeleteAccount;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String userId;



    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Change the title associated with this fragment
        getActivity().setTitle(getString(R.string.settings));

        initUi();
        return view;
    }

    private void initUi() {
        mLocation = view.findViewById(R.id.location_button);
        mLogout = view.findViewById(R.id.logout_button);
        mDeleteAccount = view.findViewById(R.id.delete_account_button);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();



        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                getActivity().finish();
            }
        });

        mDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account means complete removal of personal information associated with this account. Confirm account deletion.");
                dialog.setPositiveButton("Delete account permanently", (dialog1, which) ->

                        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                /* delete all user data in database and fire store using installed extension */
                                deleteAllData();

//                                    Toast.makeText(getActivity(), "Account Deleted Permanently", Toast.LENGTH_LONG).show();
//                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//                                    getActivity().finish();

                            } else {
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }));
                dialog.setNegativeButton("Dismiss", (dialogInterface, i) ->
                        dialogInterface.dismiss());
                AlertDialog alertDialog = dialog.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();

            }
        });

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* navigate to Location activity */
                Intent map = new Intent(getActivity(), LocationActivity.class);
                startActivity(map);
            }
        });
    }


    /**
     * Manually delete user data in fire store
     * This method is no longer used because we already installed an extension
     * to do the work for us
     */
    private void deleteAllData() {
        /* delete all data with this user in fireStore */
        db.collection("User")
                .document(userId)
                .delete().addOnSuccessListener(aVoid -> {
            Toast.makeText(getActivity(), "Account Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getActivity().finish();
        });

    }


    }


