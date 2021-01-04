package uk.ac.tees.w9312536.bukolafatunde.ui.subscribe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;
import uk.ac.tees.w9312536.bukolafatunde.model.LookupResponse;


/**
 * {@link ViewModel} for SubscribeActivity
 */
public class SubscribeViewModel extends ViewModel {

    private final PodMeRepository mRepository;
    private LiveData<LookupResponse> mLookupResponse;

    public SubscribeViewModel(PodMeRepository repository, String lookupUrl, String id) {
        mRepository = repository;
        mLookupResponse = mRepository.getLookupResponse(lookupUrl, id);
    }

    public LiveData<LookupResponse> getLookupResponse() {
        return mLookupResponse;
    }
}

