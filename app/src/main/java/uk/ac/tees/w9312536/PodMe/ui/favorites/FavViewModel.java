package uk.ac.tees.w9312536.PodMe.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import uk.ac.tees.w9312536.PodMe.data.FavoriteEntry;
import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;

/**
 * {@link ViewModel} for {@link FavoritesFragment}
 */
public class FavViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<List<FavoriteEntry>> mFavoritesEntries;

    public FavViewModel(PodMeRepository repository) {
        mRepository = repository;
        mFavoritesEntries = mRepository.getFavorites();
    }

    public LiveData<List<FavoriteEntry>> getFavorites() {
        return mFavoritesEntries;
    }
}
