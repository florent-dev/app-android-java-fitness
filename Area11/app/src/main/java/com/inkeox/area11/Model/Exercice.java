package com.inkeox.area11.Model;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName="exercices", foreignKeys = @ForeignKey(entity = Entrainement.class, parentColumns = "id", childColumns = "entrainement_id", onDelete = CASCADE))
public class Exercice implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Entrainement.class, parentColumns = "id", childColumns = "entrainement_id", onDelete = CASCADE)
    @ColumnInfo(name = "entrainement_id", index = true)
    private int idEntrainement;

    @ColumnInfo(name = "nom")
    private String nom = "Nom de l'exercice";

    @ColumnInfo(name = "icone")
    private String icone;

    @ColumnInfo(name = "temps")
    private int temps;

    @ColumnInfo(name = "temps_repos")
    private int tempsRepos;

    public Exercice(int id, String nom, String icone, int temps, int tempsRepos) {
        setId(id);
        setNom(nom);
        setIcone(icone);
        setTemps(temps);
        setTempsRepos(tempsRepos);
    }

    // Setters
    public void setId(int id) { this.id = id; }
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