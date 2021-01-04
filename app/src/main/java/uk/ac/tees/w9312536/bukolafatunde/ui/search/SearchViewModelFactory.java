package uk.ac.tees.w9312536.bukolafatunde.ui.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9312536.bukolafatunde.data.PodMeRepository;


public class SearchViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final PodMeRepository mRepository;
    private final String mSearchUrl;
    private final String mCountry;
    private final String mMedia;
    private final String mTerm;

    public SearchViewModelFactory(PodMeRepository repository, String searchUrl,
                                  String country, String media, String term) {
        mRepository = repository;
        mSearchUrl = searchUrl;
        mCountry = country;
        mMedia = media;
        mTerm = term;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new SearchViewModel(mRepository, mSearchUrl, mCountry, mMedia, mTerm);
    }
}
