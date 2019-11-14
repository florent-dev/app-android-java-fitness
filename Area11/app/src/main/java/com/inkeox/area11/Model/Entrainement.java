package com.inkeox.area11.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entrainement implements Serializable {

    private int id;
    private int preparationTemps = 10;
    private int sequenceRepetitions = 2;
    private int sequenceReposTemps = 30;
    private List<Exercice> exercices = new ArrayList<Exercice>();

    // Constructeur
    public Entrainement() {
        List<Exercice> exercices = new ArrayList<Exercice>();
    }

    // Constructeur database
    public Entrainement(int $id, int $preparationTemps, int $sequenceRepetitions, int $sequenceReposTemps) {
        this.id = $id;
        setPreparationTemps($preparationTemps);
        setSequenceRepetitions($sequenceRepetitions);
        setSequenceReposTemps($sequenceReposTemps);
    }

    public void addExercice(Exercice exercice) {
        this.exercices.add(exercice);
    }

    // Setters
    public void setPreparationTemps(int preparationTemps) { this.preparationTemps = preparationTemps; }
    public void setSequenceRepetitions(int sequenceRepetitions) { this.sequenceRepetitions = (sequenceRepetitions >= 1) ? sequenceRepetitions : 1; }
    public void setSequenceReposTemps(int sequenceReposTemps) { this.sequenceReposTemps = sequenceReposTemps; }

    // Getters
    public int getPreparationTemps() { return preparationTemps; }
    public int getSequenceRepetitions() { return sequenceRepetitions; }
    public int getSequenceReposTemps() { return sequenceReposTemps; }
    public int getExercicesCount() { return exercices.size(); }
    public List<Exercice> getExercices() { return exercices; }

}
