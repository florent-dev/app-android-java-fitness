package com.inkeox.area11.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient instance;

    // our app database object
    private static AppDatabase appDatabase;

    private DatabaseClient(Context context) {

        // creating the app database with Room database builder
        // MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "areafit-database").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
