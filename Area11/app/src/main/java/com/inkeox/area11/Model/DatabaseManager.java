package com.inkeox.area11.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Fitness.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE entrainement (" +
                    "   id integer primary key autoincrement," +
                    "   preparation_temps int not null," +
                    "   sequence_repetitions int not null," +
                    "   sequence_repos_temps int not null" +
                    ");" +
                    "CREATE TABLE exercice (" +
                    "   id integer primary key autoincrement," +
                    "   nom varchar(100) not null," +
                    "   icone varchar(50) not null," +
                    "   reposTemps int not null" +
                    "   repetitions int not null" +
                    ");" +
                    "CREATE TABLE exercices_entrainement (" +
                    "   idEntrainement integer not null," +
                    "   idExercice integer not null," +
                    ");";

        db.execSQL(sql);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing
    }

    public void insertEntrainement(Entrainement entrainement) {
        String sql = "INSERT INTO entrainement (preparation_temps, sequence_repetitions, sequence_repos_temps) " +
                     "VALUES (" + entrainement.getPreparationTemps() + ", " + entrainement.getSequenceRepetitions() + ", " + entrainement.getSequenceReposTemps() + ")";
        this.getWritableDatabase().execSQL(sql);
    }

    public void insertExercice(Exercice exercice) {
        //
    }
}
