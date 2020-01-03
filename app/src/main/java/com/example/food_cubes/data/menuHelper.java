package com.example.food_cubes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.food_cubes.data.menuContract.menuEntry.TABLE_NAME;

public class menuHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "menu.db";
    private static final int DATABASE_VERSION = 1;

    public menuHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MENU_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + menuContract.menuEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + menuContract.menuEntry.column_day + "TEXT, "
                + menuContract.menuEntry.COLUMN_BREAKFAST + "TEXT, "
                + menuContract.menuEntry.COLUMN_LUNCH + "TEXT, "
                + menuContract.menuEntry.COLUMN_DINNER + "TEXT);";
        db.execSQL(SQL_CREATE_MENU_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
