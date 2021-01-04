package uk.ac.tees.w9312536.bukolafatunde.ui.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository} and String podcast ID.
 */
public class PodcastEntryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;
    private final String mPodcastId;

    public PodcastEntryViewModelFactory(PodMeRepository repository, String podcastId) {
        mRepository = repository;
        mPodcastId = podcastId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new PodcastEntryViewModel(mRepository, mPodcastId);
    }
}
