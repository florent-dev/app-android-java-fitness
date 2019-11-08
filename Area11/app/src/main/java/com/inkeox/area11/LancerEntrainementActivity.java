package com.inkeox.area11;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inkeox.area11.Model.Compteur;
import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.OnUpdateListener;

import androidx.appcompat.app.AppCompatActivity;

public class LancerEntrainementActivity extends AppCompatActivity implements OnUpdateListener {

    // Model
    private Compteur compteur;
    private Entrainement entrainement;

    // View
    private TextView timerValue;

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer_entrainement);

        //entrainement = (Entrainement) getIntent().getExtras().getSerializable("entrainement");

        // Récupérer la view
        timerValue = (TextView) findViewById(R.id.timerValue);

        // Initialiser l'objet Compteur
        compteur = new Compteur();

        // Abonner l'activité au compteur pour "suivre" les événements
        compteur.addOnUpdateListener(this);

        // Mise à jour graphique
        miseAJour();
    }

    /**
     * Lancer le compteur
     * @param view vue
     */
    public void onStart(View view) {
        compteur.start();
    }

    /**
     * Mettre en pause le compteur
     * @param view vue
     */
    public void onPause(View view) {
        compteur.pause();
    }


    /**
     * Remettre à zéro le compteur
     * @param view
     */
    public void onReset(View view) {
        compteur.reset();
    }

    /**
     * Mise à jour graphique
     */
    private void miseAJour() {
        // Affichage des informations du compteur
        timerValue.setText("" + compteur.getMinutes() + ":"
                + String.format("%02d", compteur.getSecondes()) + ":"
                + String.format("%03d", compteur.getMillisecondes()));
    }

    /**
     * Méthode appelée à chaque update du compteur (l'activité est abonnée au compteur)
     */
    @Override
    public void onUpdate() {
        miseAJour();
    }
}
