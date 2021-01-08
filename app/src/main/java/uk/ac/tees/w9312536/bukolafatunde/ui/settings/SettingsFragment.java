package uk.ac.tees.w9312536.bukolafatunde.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import uk.ac.tees.w9312536.bukolafatunde.LoginActivity;
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
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

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
                    dialog.setMessage("Multiple deletions of this account may lead to permanent ban on this email and devices associated with this account. Confirm account deletion.");
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

