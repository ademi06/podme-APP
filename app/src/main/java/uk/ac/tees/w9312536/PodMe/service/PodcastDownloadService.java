package uk.ac.tees.w9312536.PodMe.service;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Toast;
import com.google.android.exoplayer2.offline.DownloadManager;
import com.google.android.exoplayer2.offline.DownloadManager.TaskState;
import com.google.android.exoplayer2.offline.DownloadService;
import com.google.android.exoplayer2.scheduler.PlatformScheduler;
import com.google.android.exoplayer2.scheduler.Scheduler;
import com.google.android.exoplayer2.ui.DownloadNotificationUtil;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;

import timber.log.Timber;
import uk.ac.tees.w9312536.PodMe.AppExecutors;
import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.PodMe.data.DownloadEntry;
import uk.ac.tees.w9312536.PodMe.data.PodMeDatabase;
import uk.ac.tees.w9312536.PodMe.utilities.Constants;
import uk.ac.tees.w9312536.PodMe.utilities.DownloadUtil;


/**
 * The PodcastDownloadService is to run the downloading operation.
 * Reference: @see "https://github.com/google/ExoPlayer/tree/io18"
 */
public class PodcastDownloadService extends DownloadService {

    private DownloadEntry mDownloadEntry;
    private PodMeDatabase mDb;

    public PodcastDownloadService() {
        super(
                Constants.DOWNLOAD_NOTIFICATION_ID,
                DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
                Constants.DOWNLOAD_CHANNEL_ID,
                R.string.download_channel_name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Get the database instance
        mDb = PodMeDatabase.getInstance(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Timber.e("intent in onStartCommand is null");
        } else {
            Bundle b = intent.getBundleExtra(Constants.EXTRA_DOWNLOAD_ENTRY);
            if (b != null) {
                mDownloadEntry = b.getParcelable(Constants.EXTRA_DOWNLOAD_ENTRY);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Returns a DownloadManager to be used to downloaded content.
     */
    @Override
    protected DownloadManager getDownloadManager() {
        return DownloadUtil.getDownloadManager(this);
    }

    @Nullable
    @Override
    protected Scheduler getScheduler() {
        return new PlatformScheduler(this, Constants.JOB_ID);
    }

    /**
     * Returns a progress notification to be displayed when the download service is running
     * in the foreground.
     */
    @Override
    protected Notification getForegroundNotification(TaskState[] taskStates) {
        return DownloadNotificationUtil.buildProgressNotification(
                this,
                R.drawable.ic_menu_download,
                Constants.DOWNLOAD_CHANNEL_ID,
                null,
                null,
                taskStates);
    }

    /**
     * Called when the state of a task changes.
     * Reference: @see "https://github.com/google/ExoPlayer/tree/release-v2/demos/main"
     * @param taskState The state of the task
     */
    @Override
    protected void onTaskStateChanged(TaskState taskState) {
        if (taskState.action.isRemoveAction) {
            return;
        }
        Notification notification = null;
        if (taskState.state == TaskState.STATE_COMPLETED) {
            // A notification for a completed download
            notification =
                    DownloadNotificationUtil.buildDownloadCompletedNotification(
                            this,
                            R.mipmap.ic_launcher,
                            Constants.DOWNLOAD_CHANNEL_ID,
                            null,
                            Util.fromUtf8Bytes(taskState.action.data));

            // After the download completed, inserts a downloaded episode into the database
            insertDownloadedEpisode();

        } else if (taskState.state == TaskState.STATE_FAILED) {
            // A notification for a failed download
            notification =
                    DownloadNotificationUtil.buildDownloadFailedNotification(
                            this,
                            R.mipmap.ic_launcher,
                            Constants.DOWNLOAD_CHANNEL_ID,
                            null,
                            Util.fromUtf8Bytes(taskState.action.data));
        }
        int notificationId = Constants.DOWNLOAD_NOTIFICATION_ID + 1 + taskState.taskId;
        NotificationUtil.setNotification(this, notificationId, notification);
    }

    /**
     * Inserts a downloaded episode into the downloads database after download complete.
     */
    private void insertDownloadedEpisode() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mDownloadEntry != null) {
                    // Check if this episode does not exist in the downloads database
                    // to avoid inserting the same one twice.
                    if (mDb.podcastDao().syncLoadDownload(mDownloadEntry.getItemEnclosureUrl()) == null) {
                        // Insert a downloaded episode to the database by using the podcastDao
                        mDb.podcastDao().insertDownloadedEpisode(mDownloadEntry);
                    }
                }
            }
        });

        // Show a toast message that indicates download completed
        Toast.makeText(this, getString(R.string.toast_download_completed),
                Toast.LENGTH_SHORT).show();
    }
}

