package uk.ac.tees.w9312536.PodMe.ui.add;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository} and country.
 */
public class AddPodViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;
    private final String mCountry;

    public AddPodViewModelFactory(PodMeRepository repository, String country) {
        mRepository = repository;
        mCountry = country;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddPodViewModel(mRepository, mCountry);
    }
}

