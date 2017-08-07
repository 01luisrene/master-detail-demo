package com.codepath.example.masterdetailmanual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codepath.example.masterdetailmanual.modelos.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LUIS on 07/08/2017.
 */

public class SQLiteDB extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "postsDatabase.sqlite";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_POSTS = "posts";

    // Post Table Columns
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_TITULO = "titulo";
    private static final String KEY_POST_TEXT = "text";


    SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_POST_TITULO + " TEXT ," +
                KEY_POST_TEXT + " TEXT" +
                ")";


        db.execSQL(CREATE_POSTS_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
            onCreate(db);
        }
    }

    // Insert a post into the database
    public void addPost(String id, String i, String s) {
        SQLiteDatabase db = getWritableDatabase();
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.

        ContentValues values = new ContentValues();
        values.put(KEY_POST_ID, id);
        values.put(KEY_POST_TITULO, i);
        values.put(KEY_POST_TEXT, s);

        // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
        db.insert(TABLE_POSTS, null, values);

    }

    public Cursor cargarCursorRecordatorios() {
        SQLiteDatabase db = getWritableDatabase();

        String consulta = "SELECT * FROM posts";

        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        return db.rawQuery(consulta, null);
    }

    public List<Item> getPostsList() {
        List<Item> list = new ArrayList<>();

        Cursor cursor = cargarCursorRecordatorios();

        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setId(cursor.getString(0));
            item.setTitle(cursor.getString(1));
            item.setBody(cursor.getString(2));

            list.add(item);
        }

        return list;
    }

}