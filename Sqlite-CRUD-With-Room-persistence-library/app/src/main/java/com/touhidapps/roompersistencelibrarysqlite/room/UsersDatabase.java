package com.touhidapps.roompersistencelibrarysqlite.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Touhid on 12/28/2017.
 */

@Database(entities = {UserEntity.class}, version = 3)
public abstract class UsersDatabase extends RoomDatabase {

    private static UsersDatabase usersDatabase;

    public abstract UserDao userDao();

    public static UsersDatabase getInstance(Context context) {
        if (usersDatabase == null) {
            usersDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    UsersDatabase.class, "user.db")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build();
        }

        return usersDatabase;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d(TAG, "migrate: " + database.getPageSize());

//            database.execSQL("DROP TABLE IF EXISTS " + DbConstraints.TABLE_USERS);

            database.execSQL("ALTER TABLE " + DbConstraints.TABLE_USERS + " ADD COLUMN last_update INTEGER");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // TODO
        }
    };

}

