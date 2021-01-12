package uk.ac.tees.w9312536.PodMe.ui.nowplaying;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link PodMeRepository} and enclosure url.
 */
public class DownloadEntryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;
    private final String mEnclosureUrl;

    public DownloadEntryViewModelFactory(PodMeRepository repository, String enclosureUrl) {
        mRepository = repository;
        mEnclosureUrl = enclosureUrl;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new DownloadEntryViewModel(mRepository, mEnclosureUrl);
    }
}
