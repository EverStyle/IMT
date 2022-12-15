package com.example.sqliteapp;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DB_NAME = "userResults.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "results";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE results (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_VALUE
                + " REAL, " + COLUMN_DATE + " NUMERIC);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}