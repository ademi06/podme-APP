package uk.ac.tees.w9312536.bukolafatunde.utilities;

public class Constants {

    private Constants() {
        /* Restrict instantiation */
    }

    /** Index for representing a default fragment */
    public static final int INDEX_ZERO = 0;
    /** Initial span count for the GridAutofitLayoutManager */
    public static final int GRID_SPAN_COUNT = 1;
    /** The value of column width in the GridAutofitLayoutManager */
    public static final int GRID_AUTO_FIT_COLUMN_WIDTH = 380;
    /** Default value for column width in the GridAutofitLayoutManager */
    public static final int GRID_COLUMN_WIDTH_DEFAULT = 48;
    /** Strings used to convert ITunes duration */
    public static final String SPLIT_COLON = ":";
    public static final int SPLIT_INDEX_ZERO = 0;
    public static final int SPLIT_INDEX_ONE = 1;
    public static final int SPLIT_INDEX_TWO = 2;

    /** Base URL for iTunes Search API */
    public static final String I_TUNES_BASE_URL = "https://rss.itunes.apple.com/api/v1/";


    /** URL for a lookup request */
    public static final String I_TUNES_LOOKUP = "https://itunes.apple.com/lookup";

    /** URL for search request */
    public static final String I_TUNES_SEARCH = "https://itunes.apple.com/search";

    /** The parameter value for a search field. The  "podcast" is the media type to search. */
    public static final String SEARCH_MEDIA_PODCAST = "podcast";
    /** The type of request method for reading information from the server */
    public static final String REQUEST_METHOD_GET = "GET";
    /** The pubDate pattern */
    public static final String PUB_DATE_PATTERN = "EEE, d MMM yyyy HH:mm:ss Z";
    public static final String PUB_DATE_PATTERN_TIME_ZONE = "EEE, d MMM yyyy HH:mm z";
    /** The formatted date pattern */
    public static final String FORMATTED_PATTERN = "MMM d, yyyy";
}
