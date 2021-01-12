package uk.ac.tees.w9312536.PodMe.ui.nowplaying;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.PodMe.data.DownloadEntry;
import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;


/**
 * DownloadEntryViewModel class is designed to store and manage {@link LiveData} DownloadEntry.
 */
public class DownloadEntryViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<DownloadEntry> mDownloadEntry;

    public DownloadEntryViewModel(PodMeRepository repository, String enclosureUrl) {
        mRepository = repository;
        mDownloadEntry = mRepository.getDownloadedEpisodeByEnclosureUrl(enclosureUrl);
    }

    public LiveData<DownloadEntry> getDownloadEntry() {
        return mDownloadEntry;
    }
}
