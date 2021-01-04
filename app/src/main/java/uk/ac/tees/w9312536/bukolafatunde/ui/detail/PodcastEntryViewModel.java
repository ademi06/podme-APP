package uk.ac.tees.w9312536.bukolafatunde.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;
import uk.ac.tees.w9312536.bukolafatunde.data.PodcastEntry;


/**
 * PodcastEntryViewModel class is designed to store and manage {@link LiveData} PodcastEntry.
 */
public class PodcastEntryViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<PodcastEntry> mPodcastEntry;

    public PodcastEntryViewModel(PodMeRepository repository, String podcastId) {
        mRepository = repository;
        mPodcastEntry = mRepository.getPodcastByPodcastId(podcastId);
    }

    public LiveData<PodcastEntry> getPodcastEntry() {
        return mPodcastEntry;
    }
}

