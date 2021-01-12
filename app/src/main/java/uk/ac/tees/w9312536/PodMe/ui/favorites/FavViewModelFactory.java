package uk.ac.tees.w9312536.PodMe.ui.favorites;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository}
 */
public class FavViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;

    public FavViewModelFactory(PodMeRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new FavViewModel(mRepository);
    }
}
