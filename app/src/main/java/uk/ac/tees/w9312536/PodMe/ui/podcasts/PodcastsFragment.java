package uk.ac.tees.w9312536.PodMe.ui.podcasts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.tees.w9312536.PodMe.GridAutofitLayoutManager;
import uk.ac.tees.w9312536.PodMe.data.PodcastEntry;
import uk.ac.tees.w9312536.PodMe.ui.detail.DetailActivity;
import uk.ac.tees.w9312536.PodMe.utilities.Constants;
import uk.ac.tees.w9312536.PodMe.utilities.InjectorUtils;
import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.bukolafatunde.databinding.FragmentPodcastsBinding;
import uk.ac.tees.w9312536.PodMe.ui.add.AddPodcastActivity;


/**
 * The PodcastsFragment displays the list of podcasts that the user subscribed.
 */
public class PodcastsFragment extends Fragment
        implements PodcastsAdapter.PodcastsAdapterOnClickHandler {

    /** This field is used for data binding */
    private FragmentPodcastsBinding mPodcastsBinding;

    /** Member variable for PodcastsAdapter */
    private PodcastsAdapter mPodcastsAdapter;

    /** PodcastsViewModel which stores and manages LiveData the list of PodcastEntries */
    private PodcastsViewModel mPodcastsViewModel;

    public PodcastsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPodcastsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcasts,
                container, false);
        View rootView = mPodcastsBinding.getRoot();

        // Change the title associated with this fragment
        getActivity().setTitle(getString(R.string.app_name));

        // When a FAB is clicked, start the AddPodcastActivity
        startAddPodcastActivity();

        // Create a GridAutofitLayoutManager and PodcastsAdapter, and set them to the RecyclerView
        initAdapter();

        // Hide FAB when scrolling
        hideShowFab();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setup PodcatsViewModel
        setupViewModel(this.getActivity());
    }

    /**
     * When the user clicks a FAB, start the AddPodcastActivity.
     */
    private void startAddPodcastActivity() {
        mPodcastsBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the Intent that will start the AddPodcastActivity
                Intent intent = new Intent(getContext(), AddPodcastActivity.class);
                // Once the Intent has been created, start the AddPodcastActivity
                startActivity(intent);
            }
        });
    }

    /**
     * Creates a GridAutofitLayoutManager and PodcastsAdapter, and set them to the RecyclerView.
     */
    private void initAdapter() {
        // A GridAutofitLayoutManager is responsible for calculating the amount of GridView columns
        // based on screen size and positioning item views within a RecyclerView into a grid layout.
        // Reference: @see "https://codentrick.com/part-4-android-recyclerview-grid/"
        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(
                getContext(), Constants.GRID_AUTO_FIT_COLUMN_WIDTH);
        // Set the layout for the RecyclerView to be a grid layout
        mPodcastsBinding.rvPodcasts.setLayoutManager(layoutManager);
        // Use this setting to improve performance if you know that changes in content do not
        // change the child layout size in the RecyclerView
        mPodcastsBinding.rvPodcasts.setHasFixedSize(true);

        // Initialize the adapter and attach it to the RecyclerView
        mPodcastsAdapter = new PodcastsAdapter(getContext(), this);
        mPodcastsBinding.rvPodcasts.setAdapter(mPodcastsAdapter);
    }

    /**
     * Every time the podcast data is updated, updates the UI.
     */
    private void setupViewModel(Context context) {
        // Get the ViewModel from the factory
        PodcastsViewModelFactory podcastsFactory = InjectorUtils.providePodcastsViewModelFactory(context);
        mPodcastsViewModel = ViewModelProviders.of(this, podcastsFactory)
                .get(PodcastsViewModel.class);

        // Observe the list of all {@link PodcastEntry}
        mPodcastsViewModel.getPodcasts().observe(getViewLifecycleOwner(), new Observer<List<PodcastEntry>>() {
            @Override
            public void onChanged(@Nullable List<PodcastEntry> podcastEntries) {
                // When the podcasts list is empty, show an empty view, otherwise, show podcasts.
                if (podcastEntries != null && podcastEntries.size() != 0) {
                    // Make the view for podcasts visible.
                    mPodcastsBinding.setHasPodcasts(true);

                    // Update the list of PodcastEntries and notify the adapter of any changes
                    mPodcastsAdapter.setPodcastEntries(podcastEntries);
                } else {
                    // When the podcasts list is empty, show an empty view.
                    showEmptyView();
                }
            }
        });
    }

    /**
     * This is where we receive our callback from
     * {@link PodcastsAdapter.PodcastsAdapterOnClickHandler}.
     *
     * This callback is invoked when the user clicks on a podcast in the list. When the user clicks
     * the podcast, start the DetailActivity.
     *
     * @param podcastEntry A single row from podcast table that has the data of the podcast.
     *             When the user subscribes to the podcast, the podcast data is added to the database.
     * @param imageView The shared element
     */
    @Override
    public void onPodcastClick(PodcastEntry podcastEntry, ImageView imageView) {
        // Create the Intent that will start the DetailActivity
        Intent intent = new Intent(getActivity(), DetailActivity.class);

        // Get the podcast ID, title, and the image from the podcastEntry and pass them via Intent
        String podcastId = podcastEntry.getPodcastId();
        String podcastName = podcastEntry.getTitle();
        String podcastImage = podcastEntry.getArtworkImageUrl();
        intent.putExtra(Constants.EXTRA_RESULT_ID, podcastId);
        intent.putExtra(Constants.EXTRA_RESULT_NAME, podcastName);
        intent.putExtra(Constants.EXTRA_PODCAST_IMAGE, podcastImage);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // Apply the shared element transition to the podcast image
            String transitionName = imageView.getTransitionName();
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this.getActivity(),
                    imageView,
                    transitionName
            ).toBundle();
            startActivity(intent, bundle);
        } else {
            // Once the Intent has been created, start the DetailActivity
            startActivity(intent);
        }
    }

    /**
     * When the podcasts list is empty, show an empty view.
     */
    private void showEmptyView() {
        // Show an empty view
        mPodcastsBinding.setHasPodcasts(false);
        // Set text programmatically in order to make text invisible when the user changes the menu
        // items in the navigation drawer
        mPodcastsBinding.tvEmptyPodcasts.setText(getString(R.string.empty_podcasts));
    }

    /**
     * Hides FAB when scrolling.
     * Reference: @see "https://stackoverflow.com/questions/33208613/hide-floatingactionbutton-on-scroll-of-recyclerview"
     */
    private void hideShowFab() {
        // Add a listener to be notified of any changes in scroll state
        mPodcastsBinding.rvPodcasts.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Hide FAB when the RecyclerView is scrolling
                if (dy > 0 || dy < 0 && mPodcastsBinding.fab.isShown()) {
                    mPodcastsBinding.fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // Show FAB when the RecyclerView is not currently scrolling
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mPodcastsBinding.fab.show();
                }
            }
        });
    }


}
