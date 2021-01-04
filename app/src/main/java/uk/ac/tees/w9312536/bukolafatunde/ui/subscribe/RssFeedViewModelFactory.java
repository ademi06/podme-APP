package uk.ac.tees.w9312536.bukolafatunde.ui.subscribe;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository} and String feedUrl.
 */
public class RssFeedViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;
    private final String mFeedUrl;

    public RssFeedViewModelFactory(PodMeRepository repository, String feedUrl) {
        mRepository = repository;
        mFeedUrl = feedUrl;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new RssFeedViewModel(mRepository, mFeedUrl);
    }
}

