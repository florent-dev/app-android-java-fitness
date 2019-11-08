package iut2.tp;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import iut2.tp.exercice4Data.Jeu;
import iut2.tp.exercice4Data.Resultat;

public class Exercice3Activity extends AppCompatActivity {

    private Resultat resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice3);

        // On créé notre instance Resultat
        resultat = new Resultat();
    }

    /**
     * Lorsqu'un élément joueur est désigné
      */
    public void onClickShifoumi(View view) {
        TextView resultatTexte = (TextView) findViewById(R.id.resultat);

        // Reset de la visiblité des choix ordi
        ImageView pierreOrdi = (ImageView) findViewById(R.id.shifoumi_ordi_pierre);
        ImageView papierOrdi = (ImageView) findViewById(R.id.shifoumi_ordi_papier);
        ImageView ciseauxOrdi = (ImageView) findViewById(R.id.shifoumi_ordi_ciseaux);
        pierreOrdi.setVisibility(View.INVISIBLE);
        papierOrdi.setVisibility(View.INVISIBLE);
        ciseauxOrdi.setVisibility(View.INVISIBLE);

        // Reset de la couleur des choix joueurs
        ImageView pierreJoueur = (ImageView) findViewById(R.id.shifoumi_joueur_pierre);
        ImageView papierJoueur = (ImageView) findViewById(R.id.shifoumi_joueur_papier);
        ImageView ciseauxJoueur = (ImageView) findViewById(R.id.shifoumi_joueur_ciseaux);
        pierreJoueur.setBackgroundColor(Color.parseColor("#FFD3DBD8"));
        papierJoueur.setBackgroundColor(Color.parseColor("#FFD3DBD8"));
        ciseauxJoueur.setBackgroundColor(Color.parseColor("#FFD3DBD8"));

        // Jeu initialisé
        Jeu jeu = new Jeu();
        ImageView choixJoueur = (ImageView) findViewById(view.getId());

        int choixJoueurId;
        switch (choixJoueur.getId()) {
            case R.id.shifoumi_joueur_pierre: choixJoueurId = 0; break;
            case R.id.shifoumi_joueur_ciseaux: choixJoueurId = 1; break;
            default: choixJoueurId = 2;
        }

        jeu.setMainJoueur(choixJoueurId);

        // On affiche le choix de l'ordi
        switch (jeu.getMainOrdinateur()) {
            case Jeu.PIERRE: pierreOrdi.setVisibility(View.VISIBLE); break;
            case Jeu.PAPIER: papierOrdi.setVisibility(View.VISIBLE); break;
            case Jeu.CISEAUX: ciseauxOrdi.setVisibility(View.VISIBLE); break;
        }

        // On affiche le choix du joueur
        choixJoueur.setBackgroundColor(Color.parseColor("#36AC80"));

        if (jeu.egalite()) {
            resultatTexte.setText(R.string.shifoumi_egalite);
            resultat.addEgalite();
        } else {
            if (jeu.joueurGagne()) {
                resultatTexte.setText(R.string.shifoumi_gagne);
                resultat.addVictoire();
            } else {
                resultatTexte.setText(R.string.shifoumi_defaite);
                resultat.addDefaite();
            }
        }

        // On affiche les résultats
        TextView resultatsPanel = (TextView) findViewById(R.id.resultats_chiffre);
        resultatsPanel.setText("--- Résultats ---\nVictoires : " + resultat.getNombreVictoire() + "\nDéfaites : " + resultat.getNombreDefaite() + "\nEgalites : " + resultat.getNombreEgalite());

    }
}
