package uk.ac.tees.w9312536.bukolafatunde.utilities;

import android.content.Context;

import com.google.android.exoplayer2.offline.DownloadManager;
import com.google.android.exoplayer2.offline.ProgressiveDownloadAction;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

import uk.ac.tees.w9312536.bukolafatunde.R;

/**
 * Reference: @see "https://github.com/google/ExoPlayer/tree/io18"
 */
public class DownloadUtil {

    private static Cache sCache;
    private static DownloadManager sDownloadManager;

    /**
     * As we're sharing the same cache for playback and downloading, we have a singleton for our
     * whole process.
     */
    public static synchronized Cache getCache(Context context) {
        if (sCache == null) {
            // Create a file which points to a directory where we're going to store the downloads
            File cacheDirectory = new File(context.getExternalFilesDir(null), Constants.FILE_DOWNLOADS);
            // Instantiate a simple cache
            sCache = new SimpleCache(cacheDirectory, new NoOpCacheEvictor());
        }
        return sCache;
    }

    /**
     * Creates a singleton instance of a DownloadManager for our whole process.
     */
    public static synchronized DownloadManager getDownloadManager(Context context) {
        if (sDownloadManager == null) {
            // Create a file, because it needs to persist some information about downloads that are
            // in progress
            File actionFile = new File(context.getExternalCacheDir(), Constants.FILE_ACTIONS);
            // Instantiate a DownloadManager
            sDownloadManager =
                    new DownloadManager(
                            // The same cache as the one we're using for the cache during playback
                            getCache(context),
                            // DataSourceFactory for loading data to populate the cache
                            new DefaultDataSourceFactory(
                                    context,
                                    Util.getUserAgent(context, context.getString(R.string.app_name))),
                            actionFile,
                            // Deserializer for progressive download action, because we're downloading
                            // progressive media
                            ProgressiveDownloadAction.DESERIALIZER);
        }
        return sDownloadManager;
    }
}

