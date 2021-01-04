package uk.ac.tees.w9312536.bukolafatunde.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import timber.log.Timber;

import static uk.ac.tees.w9312536.bukolafatunde.utilities.Constants.DATABASE_NAME;


@Database(entities = {PodcastEntry.class, FavoriteEntry.class, DownloadEntry.class}, version = 1, exportSchema = false)
@TypeConverters({ItemsConverter.class, DateConverter.class})
public abstract class PodMeDatabase extends RoomDatabase {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static PodMeDatabase sInstance;

    public static PodMeDatabase getInstance(Context context) {
        Timber.d("Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PodMeDatabase.class, DATABASE_NAME).build();
                Timber.d("Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract PodcastDao podcastDao();
}

