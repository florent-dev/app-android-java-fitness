package com.inkeox.area11.Database;

import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.Exercice;
import com.inkeox.area11.Repository.EntrainementDAO;
import com.inkeox.area11.Repository.ExerciceDAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entrainement.class, Exercice.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntrainementDAO entrainementDAO();
    public abstract ExerciceDAO exerciceDAO();
}