package com.example.a51youtube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "userdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "USERS";
    private static final String ID_COL = "id";
    private static final String FULLNAME_COL = "fullname";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";

    public UserDbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public UserDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users table
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FULLNAME_COL + " TEXT NOT NULL,"
                + USERNAME_COL + " TEXT NOT NULL,"
                + PASSWORD_COL + " TEXT NOT NULL)";
        db.execSQL(query);
    }

    // Method to add a new user
    public long addNewUSER(USER user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FULLNAME_COL, user.getFullName());
        values.put(USERNAME_COL, user.getUsername());
        values.put(PASSWORD_COL, user.getPassword());

        long id = db.insert(TABLE_NAME, null, values);

        db.close();
        return id;
    }

    // Method to validate a user
    public long validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME_COL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        long userId = -1;

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD_COL));

            if (password.equals(storedPassword)) {
                userId = cursor.getLong(cursor.getColumnIndexOrThrow(ID_COL));
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return userId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and create a new one if upgrading
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
