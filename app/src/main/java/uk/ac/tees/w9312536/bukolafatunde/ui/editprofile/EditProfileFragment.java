package uk.ac.tees.w9312536.bukolafatunde.ui.editprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import uk.ac.tees.w9312536.bukolafatunde.R;

public class EditProfileFragment extends Fragment {

    View view;
    TextView tvEmpty;


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
       // tvEmpty = view.findViewById(R.id.tv_empty_downloads);
    }
}

