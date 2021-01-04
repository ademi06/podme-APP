package uk.ac.tees.w9312536.bukolafatunde.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;
import uk.ac.tees.w9312536.bukolafatunde.model.ITunesResponse;


/**
 * {@link ViewModel} for AddPodcastActivity
 */
public class AddPodViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<ITunesResponse> mITunesResponse;

    public AddPodViewModel(PodMeRepository repository, String country) {
        mRepository = repository;
        mITunesResponse = mRepository.getITunesResponse(country);
    }

    public LiveData<ITunesResponse> getITunesResponse() {
        return mITunesResponse;
    }

    /**
     * Sets a new value for a country.
     */
    public void setCountry(String country) {
        mITunesResponse = mRepository.getITunesResponse(country);
    }
}

