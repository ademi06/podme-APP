package uk.ac.tees.w9312536.bukolafatunde.ui.podcasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.bukolafatunde.ui.add.AddPodcastActivity;


public class PodcastsFragment extends Fragment {

    /** This field is used for data binding */
    //private FragmentPodcastsBinding mPodcastsBinding;
    View view;
    RecyclerView rvPodcast;
    TextView tvEmpty;
    FloatingActionButton addFab;

    public PodcastsFragment() {
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
        view = inflater.inflate(R.layout.fragment_podcasts, container, false);

        initUi();
        return view;
    }

    private void initUi() {
        rvPodcast = view.findViewById(R.id.rv_podcasts);
        tvEmpty = view.findViewById(R.id.tv_empty_podcasts);
        addFab = view.findViewById(R.id.fab);

        addPodcast();
        initAdapter();
    }

    private void initAdapter() {

    }

    private void addPodcast() {
        addFab.setOnClickListener(v -> {
            //
            Intent add = new Intent(getContext(), AddPodcastActivity.class);
            startActivity(add);
        });
    }
}
