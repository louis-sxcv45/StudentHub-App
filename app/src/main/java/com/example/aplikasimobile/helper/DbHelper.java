package com.example.aplikasimobile.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.aplikasimobile.DataView;
import java.util.ArrayList;
import java.util.HashMap;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "datstudent.db";
    private static final String TABLE_SQLite = "student_data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTH = "birth";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_ADDRESS = "address";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(Context applicationContext) {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_BIRTH + " TEXT NOT NULL, " +
                COLUMN_GENDER + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL " +
                ")";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NUMBER, cursor.getString(1));
                map.put(COLUMN_NAME, cursor.getString(2));
                map.put(COLUMN_BIRTH, cursor.getString(3));
                map.put(COLUMN_GENDER,cursor.getString(4));
                map.put(COLUMN_ADDRESS, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e( "select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }
    public void insert(String number, String name, String birth, String gender, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (number, name, birth , gender ,address) " +
                "VALUES ('" + number + "', '" + name + "', '" + birth + "', '" + gender + "', '" + address + "')";
        Log.e("insert sqlite ","" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String number, String name, String birth, String gender, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_SQLite + " SET " +
                COLUMN_NUMBER + "='" + number + "', " +
                COLUMN_NAME + "='" + name + "', " +
                COLUMN_BIRTH + "='" + birth + "', " +
                COLUMN_GENDER + "='" + gender + "', " +
                COLUMN_ADDRESS + "='" + address + "' " +
                "WHERE " + COLUMN_ID + "='" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "='" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}
