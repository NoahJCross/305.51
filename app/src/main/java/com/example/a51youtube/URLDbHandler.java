package com.example.a51youtube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class URLDbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "urldb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "URLS";
    private static final String ID_COL = "id";
    private static final String USER_ID_COL = "userid";
    private static final String URL_COL = "url";

    public URLDbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public URLDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the URLS table
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_ID_COL + " INTEGER NOT NULL,"
                + URL_COL + " TEXT NOT NULL, "
                + " FOREIGN KEY (" + USER_ID_COL + ") REFERENCES users(" + ID_COL + ")"
                + ")";
        db.execSQL(query);
    }

    // Add a new URL to the database
    public void addNewURL(URL url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(URL_COL, url.getURL());
        values.put(USER_ID_COL, url.getUserId());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Get all URLs by user ID
    public List<URL> getAllURLSByUserId(long userId) {
        List<URL> urlList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID_COL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                URL url = new URL();
                url.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ID_COL)));
                url.setUserId(cursor.getLong(cursor.getColumnIndexOrThrow(USER_ID_COL)));
                url.setURL(cursor.getString(cursor.getColumnIndexOrThrow(URL_COL)));
                urlList.add(url);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return urlList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it if the database version is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
