package uk.ac.tees.w9312536.PodMe.ui.subscribe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;
import uk.ac.tees.w9312536.PodMe.model.rss.RssFeed;


/**
 * RssFeedViewModel class is designed to store and manage {@link LiveData} RssFeed
 */
public class RssFeedViewModel extends ViewModel {

    private final LiveData<RssFeed> mRssFeed;

    private final PodMeRepository mRepository;

    public RssFeedViewModel(PodMeRepository repository, String feedUrl) {
        mRepository = repository;
        mRssFeed = mRepository.getRssFeed(feedUrl);
    }

    public LiveData<RssFeed> getRssFeed() {
        return mRssFeed;
    }
}
