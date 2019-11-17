package com.inkeox.area11.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="entrainements")
public class Entrainement implements Serializable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "preparation_temps")
    private int preparationTemps = 10;

    @ColumnInfo(name = "sequence_repetitions")
    private int sequenceRepetitions = 2;

    @ColumnInfo(name = "sequence_repos_temps")
    private int sequenceReposTemps = 30;

    @Ignore
    private List<Exercice> exercices = new ArrayList<Exercice>();

    @Ignore
    public Entrainement() {
        List<Exercice> exercices = new ArrayList<Exercice>();
    }

    public Entrainement(int id, int preparationTemps, int sequenceRepetitions, int sequenceReposTemps) {
        setId(id);
        setPreparationTemps(preparationTemps);
        setSequenceRepetitions(sequenceRepetitions);
        setSequenceReposTemps(sequenceReposTemps);
    }

    public void addExercice(Exercice exercice) {
        this.exercices.add(exercice);
    }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setPreparationTemps(int preparationTemps) { this.preparationTemps = preparationTemps; }
    public void setSequenceRepetitions(int sequenceRepetitions) { this.sequenceRepetitions = (sequenceRepetitions >= 1) ? sequenceRepetitions : 1; }
    public void setSequenceReposTemps(int sequenceReposTemps) { this.sequenceReposTemps = sequenceReposTemps; }

    // Getters
    public int getId() { return id; }
    public int getPreparationTemps() { return preparationTemps; }
    public int getSequenceRepetitions() { return sequenceRepetitions; }
    public int getSequenceReposTemps() { return sequenceReposTemps; }
    public int getExercicesCount() { return exercices.size(); }
    public List<Exercice> getExercices() { return exercices; }

}
