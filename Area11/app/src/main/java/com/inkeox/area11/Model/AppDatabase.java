package com.inkeox.area11.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entrainement.class, Exercice.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntrainementDAO getEntrainementDAO();
    public abstract ExerciceDAO getExerciceDAO();
}