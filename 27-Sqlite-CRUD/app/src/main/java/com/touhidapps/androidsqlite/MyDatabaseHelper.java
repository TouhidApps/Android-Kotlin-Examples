package com.touhidapps.androidsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by touhid on 8/3/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = MyDatabaseHelper.class.getSimpleName();
    private final Context mContext;

    private static final String MY_DATABASE_NAME = "my_data.db";
    private static final int MY_DATABASE_VERSION = 1;

    public static final String TABLE_NAME_INFO = "info";

    public static final String TABLE_INFO_NAME = "name";
    public static final String TABLE_INFO_PHONE = "phone";

    private static final String SQL_TABLE_INFO = "CREATE TABLE " + TABLE_NAME_INFO + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_INFO_NAME + " TEXT NOT NULL,"
            + TABLE_INFO_PHONE + " TEXT NOT NULL)";

    public MyDatabaseHelper(Context context) {
        super(context, MY_DATABASE_NAME, null, MY_DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_INFO);
        onCreate(sqLiteDatabase);
    }



}
