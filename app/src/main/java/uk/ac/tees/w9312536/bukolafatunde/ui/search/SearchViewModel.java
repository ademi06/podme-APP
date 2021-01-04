package uk.ac.tees.w9312536.bukolafatunde.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;
import uk.ac.tees.w9312536.bukolafatunde.model.SearchResponse;


public class SearchViewModel extends ViewModel {

    private final PodMeRepository mRespository;
    private LiveData<SearchResponse> mSearchResponse;

    public SearchViewModel(PodMeRepository repository, String searchUrl,
                           String country, String media, String term) {
        mRespository = repository;
        mSearchResponse = mRespository.getSearchResponse(searchUrl, country, media, term);
    }

    public LiveData<SearchResponse> getSearchResponse() {
        return mSearchResponse;
    }
}
