package uk.ac.tees.w9312536.bukolafatunde.ui.nowplaying;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.bukolafatunde.data.FavoriteEntry;
import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;


/**
 * FavoriteEntryViewModel class is designed to store and manage {@link LiveData} FavoriteEntry.
 */
public class FavoriteEntryViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<FavoriteEntry> mFavoriteEntry;

    public FavoriteEntryViewModel(PodMeRepository repository, String url) {
        mRepository = repository;
        mFavoriteEntry = mRepository.getFavoriteEpisodeByUrl(url);
    }

    public LiveData<FavoriteEntry> getFavoriteEntry() {
        return mFavoriteEntry;
    }
}
