package com.touhidapps.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by touhid on 8/3/17.
 */

public class SqliteController {

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public SqliteController(Context context) {
        this.mContext = context;
        myDatabaseHelper = new MyDatabaseHelper(context);
    }

    public long addInfo(InfoModel infoModel) {
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.TABLE_INFO_NAME, infoModel.getName());
        contentValues.put(MyDatabaseHelper.TABLE_INFO_PHONE, infoModel.getPhone());
        long row = sqLiteDatabase.insert(MyDatabaseHelper.TABLE_NAME_INFO, null, contentValues);
        sqLiteDatabase.close();
        Log.d("Row No", "addInfo: " + row);
        return row;
    }

    public List<InfoModel> getAllInfo() {
        sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
        List<InfoModel> infoModels = new ArrayList<>();
        String sql = "SELECT * FROM " + MyDatabaseHelper.TABLE_NAME_INFO;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                InfoModel im = new InfoModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );

                infoModels.add(im);

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return infoModels;
    }

    public long updateInfo(InfoModel infoModel) {
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseColumns._ID, infoModel.getId());
        contentValues.put(MyDatabaseHelper.TABLE_INFO_NAME, infoModel.getName());
        contentValues.put(MyDatabaseHelper.TABLE_INFO_PHONE, infoModel.getPhone());
        long row = sqLiteDatabase.update(MyDatabaseHelper.TABLE_NAME_INFO, contentValues,
                BaseColumns._ID + "=?", new String[]{String.valueOf(infoModel.getId())});
        // db.update(TABLE_NAME, contentValues, NAME + " = ? AND " + LASTNAME + " = ?", new String[]{"Manas", "Bajaj"});
        sqLiteDatabase.close();
        return row;
    }

    public long deleteInfo(long id) {
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        long row = sqLiteDatabase.delete(MyDatabaseHelper.TABLE_NAME_INFO,
                BaseColumns._ID + "=?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return row;
    }


}
