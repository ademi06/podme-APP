package uk.ac.tees.w9312536.bukolafatunde.ui.podcasts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;
import uk.ac.tees.w9312536.bukolafatunde.data.PodcastEntry;

/**
 * PodcastsViewModel class is designed to store and manage {@link LiveData} the list of PodcastEntries.
 */
public class PodcastsViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private final LiveData<List<PodcastEntry>> mPodcastEntries;

    public PodcastsViewModel(PodMeRepository repository) {
        mRepository = repository;
        mPodcastEntries = mRepository.getPodcasts();
    }

    public LiveData<List<PodcastEntry>> getPodcasts() {
        return mPodcastEntries;
    }
}

