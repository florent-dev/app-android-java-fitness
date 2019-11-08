package com.inkeox.compteur.main.java.mi1.test_countdowntimer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inkeox.compteur.R;
import com.inkeox.compteur.main.java.mi1.test_countdowntimer.data.Compteur;
import com.inkeox.compteur.main.java.mi1.test_countdowntimer.data.OnUpdateListener;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements OnUpdateListener {

    // VIEW
    private TextView timerValue;

    // DATA
    private Compteur compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer la view
        timerValue = (TextView) findViewById(R.id.timerValue);

        // Initialiser l'objet Compteur
        compteur = new Compteur();

        // Abonner l'activité au compteur pour "suivre" les événements
        compteur.addOnUpdateListener(this);

        // Mise à jour graphique
        miseAJour();
    }

    // Lancer le compteur
    public void onStart(View view) {
        compteur.start();
    }

    // Mettre en pause le compteur
    public void onPause(View view) {
        compteur.pause();
    }


    // Remettre à zéro le compteur
    public void onReset(View view) {
        compteur.reset();
    }

    // Mise à jour graphique
    private void miseAJour() {

        // Affichage des informations du compteur
        timerValue.setText("" + compteur.getMinutes() + ":"
                + String.format("%02d", compteur.getSecondes()) + ":"
                + String.format("%03d", compteur.getMillisecondes()));

    }

    /**
     * Méthode appelée à chaque update du compteur (l'activité est abonnée au compteur)
     *
     */
    @Override
    public void onUpdate() {
        miseAJour();
    }
}
