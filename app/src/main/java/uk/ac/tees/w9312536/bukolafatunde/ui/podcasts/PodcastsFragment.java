package uk.ac.tees.w9312536.bukolafatunde.ui.podcasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uk.ac.tees.w9312536.bukolafatunde.R;
//import uk.ac.tees.w9312536.bukolafatunde.databinding.FragmentPodcastsBinding;


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
//        mPodcastsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcasts,
//                container, false);
//        View rootView = mPodcastsBinding.getRoot();
//
//        // Change the title associated with this fragment
//        getActivity().setTitle(getString(R.string.app_name));
//
//        return rootView;
        initUi();
        return view;
    }

    private void initUi() {
        rvPodcast = view.findViewById(R.id.rv_podcasts);
        tvEmpty = view.findViewById(R.id.tv_empty_podcasts);
        addFab = view.findViewById(R.id.fab);
    }
}
