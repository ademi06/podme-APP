package uk.ac.tees.w9312536.PodMe.utilities;

import android.content.Context;

import uk.ac.tees.w9312536.PodMe.AppExecutors;
import uk.ac.tees.w9312536.PodMe.ui.nowplaying.DownloadEntryViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.nowplaying.FavoriteEntryViewModelFactory;
import uk.ac.tees.w9312536.PodMe.data.PodMeDatabase;
import uk.ac.tees.w9312536.PodMe.data.PodMeRepository;
import uk.ac.tees.w9312536.PodMe.ui.add.AddPodViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.detail.PodcastEntryViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.downloads.DownloadsViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.favorites.FavViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.podcasts.PodcastsViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.search.SearchViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.subscribe.RssFeedViewModelFactory;
import uk.ac.tees.w9312536.PodMe.ui.subscribe.SubscribeViewModelFactory;


/**
 * Provides static methods to inject the various classes needed for CandyPod.
 */
public class InjectorUtils {

    public static PodMeRepository provideRepository(Context context) {
        PodMeDatabase database = PodMeDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        ITunesSearchApi iTunesSearchApi = RetrofitClient.getClient().create(ITunesSearchApi.class);
        return PodMeRepository.getInstance(database.podcastDao(), iTunesSearchApi, executors);
    }

    public static AddPodViewModelFactory provideAddPodViewModelFactory(Context context, String country) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new AddPodViewModelFactory(repository, country);
    }

    public static SubscribeViewModelFactory provideSubscribeViewModelFactory(
            Context context, String lookupUrl, String id) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new SubscribeViewModelFactory(repository, lookupUrl, id);
    }

    public static RssFeedViewModelFactory provideRssViewModelFactory(Context context, String feedUrl) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new RssFeedViewModelFactory(repository, feedUrl);
    }

    public static PodcastEntryViewModelFactory providePodcastEntryViewModelFactory(
            Context context, String podcastId) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new PodcastEntryViewModelFactory(repository, podcastId);
    }

    public static PodcastsViewModelFactory providePodcastsViewModelFactory(Context context) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new PodcastsViewModelFactory(repository);
    }

    public static FavoriteEntryViewModelFactory provideFavoriteEntryViewModelFactory(Context context, String url) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new FavoriteEntryViewModelFactory(repository, url);
    }

    public static FavViewModelFactory provideFavViewModelFactory(Context context) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new FavViewModelFactory(repository);
    }

    public static DownloadsViewModelFactory provideDownloadsViewModelFactory(Context context) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new DownloadsViewModelFactory(repository);
    }

    public static DownloadEntryViewModelFactory provideDownloadEntryViewModelFactory(
            Context context, String enclosureUrl) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new DownloadEntryViewModelFactory(repository, enclosureUrl);
    }

    public static SearchViewModelFactory provideSearchViewModelFactory(
            Context context, String searchUrl, String country, String media, String term) {
        PodMeRepository repository = provideRepository(context.getApplicationContext());
        return new SearchViewModelFactory(repository, searchUrl, country, media, term);
    }
}

