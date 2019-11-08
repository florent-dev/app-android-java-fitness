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

    private final Compteur compteur = new Compteur();

    /**
     * Constructeur
     */
    public Entrainement() {
        List<Exercice> exercices = new ArrayList<Exercice>();
    }

    public int getPreparationTemps() {
        return preparationTemps;
    }

    public void setPreparationTemps(int preparationTemps) {
        this.preparationTemps = preparationTemps;
    }

    public int getSequenceRepetitions() {
        return sequenceRepetitions;
    }

    public void setSequenceRepetitions(int sequenceRepetitions) {
        this.sequenceRepetitions = sequenceRepetitions;
    }

    public int getSequenceReposTemps() {
        return sequenceReposTemps;
    }

    public void setSequenceReposTemps(int sequenceReposTemps) {
        this.sequenceReposTemps = sequenceReposTemps;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void addExercice(Exercice exercice) {
        this.exercices.add(exercice);
    }
}
