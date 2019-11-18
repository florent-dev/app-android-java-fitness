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
    public List<Entrainement> getAll();

    @Query("SELECT * from entrainements where id = :id LIMIT 1")
    public Entrainement getById(int id);

    @Query("SELECT * from exercices where entrainement_id = :id LIMIT 1")
    public List<Exercice> getExercices(int id);

    @Insert
    public void insert(Entrainement entrainement);

    @Update
    public void update(Entrainement entrainement);

    @Delete
    public void delete(Entrainement entrainement);
}
