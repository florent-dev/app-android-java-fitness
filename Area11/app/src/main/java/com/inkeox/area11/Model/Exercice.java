package com.inkeox.area11.Model;

import java.io.Serializable;

public class Exercice implements Serializable {

    private int id;
    private int idEntrainement;
    private String nom = "Nom de l'exercice";
    private String icone;

    private int temps;
    private int tempsRepos;

    // Constructeur
    public Exercice(String nom, String icone, int temps, int tempsRepos) {
        this.nom = nom;
        this.icone = icone;
        this.temps = temps;
        this.tempsRepos = tempsRepos;
    }

    // Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setIcone(String icone) { this.icone = icone; }
    public void setTemps(int temps) { this.temps = temps; }
    public void setTempsRepos(int tempsRepos) { this.tempsRepos = tempsRepos; }
    public void setIdEntrainement(int idEntrainement) { this.idEntrainement = idEntrainement; }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getIcone() { return icone; }
    public int getTemps() { return temps; }
    public int getTempsRepos() { return tempsRepos; }
    public int getIdEntrainement() { return idEntrainement; }
}