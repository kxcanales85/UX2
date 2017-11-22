package com.example.bustamante.unifitv2.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bustamante.unifitv2.Models.Alimento;

/**
 * Created by bustamante on 21-11-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "appDataBase.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Alimento.TABLE  + "("
                + Alimento.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Alimento.KEY_nombre + " TEXT, "
                + Alimento.KEY_carbos + " INTEGER, "
                + Alimento.KEY_proteinas + " INTEGER, "
                + Alimento.KEY_grasas + " INTEGER,"
                + Alimento.KEY_porcion + "INTEGER )";
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Alimento.TABLE);

        // Create tables again
        onCreate(db);

    }
}
