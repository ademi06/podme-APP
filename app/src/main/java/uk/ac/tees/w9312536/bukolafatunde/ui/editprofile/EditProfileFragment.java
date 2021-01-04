package uk.ac.tees.w9312536.bukolafatunde.ui.editprofile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import pub.devrel.easypermissions.EasyPermissions;
import uk.ac.tees.w9312536.bukolafatunde.R;

public class EditProfileFragment extends Fragment {

    View view;
    EditText etName, etAboutMe;
    CircularImageView mProfileImage;
    Button saveButton;

    /* Image request code */
    private int PICK_IMAGE_REQUEST = 1;
    /* storage permission code */
    private static final int RC_STORAGE_PERM = 101;


    public EditProfileFragment() {
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
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        initUi();
        return view;
    }

    private void initUi() {
        etName = view.findViewById(R.id.edit_text_profile_edit_name);
        mProfileImage = view.findViewById(R.id.profile_image);
        etAboutMe = view.findViewById(R.id.edit_text_profile_about_me);
        saveButton = view.findViewById(R.id.save_profile_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFireStore();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFireStore();
            }
        });

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

    }

    private void loadImage() {
        // check storage and camera permission
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            // Have permission, do the thing!
            showFileChooser();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions((Activity) getContext(), getString(R.string.rationale_storage),
                    RC_STORAGE_PERM, perms);
        }
    }

    /* method to show file chooser */
    private void showFileChooser() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE_REQUEST);
    }

    private void saveToFireStore() {
        String name = etName.getText().toString();
        String aboutMe = etAboutMe.getText().toString();
    }
}

