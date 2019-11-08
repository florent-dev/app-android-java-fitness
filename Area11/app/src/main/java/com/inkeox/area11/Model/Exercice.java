package com.inkeox.area11.Model;

import java.io.Serializable;

public class Exercice implements Serializable {

    private int id;
    private int idEntrainement;
    private String nom = "Nom de l'exercice";
    private String icone;
    private int reposTemps;
    private int repetitions;

    public Exercice(String nom, String icone, int reposTemps, int repetitions) {
        this.nom = nom;
        this.icone = icone;
        this.reposTemps = reposTemps;
        this.repetitions = repetitions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(int idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public int getReposTemps() {
        return reposTemps;
    }

    public void setReposTemps(int reposTemps) {
        this.reposTemps = reposTemps;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}