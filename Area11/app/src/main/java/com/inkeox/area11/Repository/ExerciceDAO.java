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
public interface ExerciceDAO {
    @Query("SELECT * from exercices")
    public List<Exercice> getAll();

    @Query("SELECT * from exercices where entrainement_id = :entrainementId")
    public List<Exercice> getByEntrainementId(int entrainementId);

    @Insert
    public void insert(Entrainement entrainement);

    @Update
    public void update(Entrainement entrainement);

    @Delete
    public void delete(Entrainement entrainement);
}
