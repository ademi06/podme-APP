package uk.ac.tees.w9312536.bukolafatunde.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import uk.ac.tees.w9312536.bukolafatunde.R;
//import uk.ac.tees.w9312536.bukolafatunde.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {
    /** This field is used for data binding */
   // private FragmentFavoritesBinding mFavoritesBinding;
    View view;
    RecyclerView rvFavorites;
    TextView tvEmpty;

    public FavoritesFragment() {
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
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
//        mFavoritesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites,
//                container, false);
//        View rootView = mFavoritesBinding.getRoot();
//
//        // Change the title associated with this fragment
//        getActivity().setTitle("Favorites");
//
//        return rootView;
        initUi();
        return view;
    }

    private void initUi() {
        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvEmpty = view.findViewById(R.id.tv_empty_favorites);
    }
}
