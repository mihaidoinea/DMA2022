package ro.csie.en.g1084s04;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Movie.class}, exportSchema = false, version = 1)
@TypeConverters({DateConverter.class})
public abstract class DatabaseManager extends RoomDatabase {

    private static final String DB_NAME = "g1084_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context)
    {
        if(databaseManager == null)
        {
            synchronized (DatabaseManager.class)
            {
                databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return databaseManager;
    }
    public abstract MovieDao getMovieDao();
}
