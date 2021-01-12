package uk.ac.tees.w9312536.PodMe.ui.podcasts;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository}.
 */
public class PodcastsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;

    public PodcastsViewModelFactory(PodMeRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new PodcastsViewModel(mRepository);
    }
}
