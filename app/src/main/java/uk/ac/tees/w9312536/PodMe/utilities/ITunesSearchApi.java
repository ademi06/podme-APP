package uk.ac.tees.w9312536.PodMe.utilities;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import uk.ac.tees.w9312536.PodMe.model.ITunesResponse;
import uk.ac.tees.w9312536.PodMe.model.LookupResponse;
import uk.ac.tees.w9312536.PodMe.model.SearchResponse;
import uk.ac.tees.w9312536.PodMe.model.rss.RssFeed;

public interface ITunesSearchApi {

    @GET("{country}/podcasts/top-podcasts/all/25/explicit.json") @Json
    Call<ITunesResponse> getTopPodcasts(
            @Path("country") String country
    );

    /**
     * Reference: @see "https://stackoverflow.com/questions/32559333/retrofit-2-dynamic-url"
     * @param url A complete URL for an endpoint
     * @param id The id is used to create a lookup request to search for a specific podcast
     */
    @GET @Json
    Call<LookupResponse> getLookupResponse(
            @Url String url,
            @Query("id") String id
    );

    @GET @Json
    Call<SearchResponse> getSearchResponse(
            @Url String searchUrl,
            @Query("country") String country,
            @Query("media") String media,
            @Query("term") String term
    );

    @GET @Xml
    Call<RssFeed> getRssFeed(
            @Url String url
    );

    @Retention(RetentionPolicy.RUNTIME)
    @interface Json {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface Xml {}
}
