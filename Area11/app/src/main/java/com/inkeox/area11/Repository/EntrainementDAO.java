package com.inkeox.area11.Repository;

import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.Exercice;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EntrainementDAO {
    @Query("SELECT * from entrainements")
    List<Entrainement> getAll();

    @Query("SELECT * from entrainements where id = :id LIMIT 1")
    Entrainement getById(int id);

    @Query("SELECT * from exercices where entrainement_id = :id LIMIT 1")
    List<Exercice> getExercices(int id);

    @Query("SELECT id from entrainements ORDER BY id DESC LIMIT 1")
    int getLastId();

    @Insert
    void insert(Entrainement entrainement);

    @Update
    void update(Entrainement entrainement);

    @Delete
    void delete(Entrainement entrainement);
}
