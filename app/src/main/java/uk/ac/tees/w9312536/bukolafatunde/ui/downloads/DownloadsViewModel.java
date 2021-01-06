package uk.ac.tees.w9312536.bukolafatunde.ui.downloads;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import uk.ac.tees.w9312536.bukolafatunde.data.DownloadEntry;
import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;

/**
 * {@link ViewModel} for {@link DownloadsFragment}
 */
public class DownloadsViewModel extends ViewModel {
    private final PodMeRepository mRepository;
    private final LiveData<List<DownloadEntry>> mDownloadEntries;

    public DownloadsViewModel(PodMeRepository repository) {
        mRepository = repository;
        mDownloadEntries = mRepository.getDownloads();
    }

    public LiveData<List<DownloadEntry>> getDownloads() {
        return mDownloadEntries;
    }
}
