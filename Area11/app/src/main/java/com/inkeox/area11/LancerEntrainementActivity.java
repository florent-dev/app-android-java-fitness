package com.inkeox.area11;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inkeox.area11.Model.Compteur;
import com.inkeox.area11.Model.Entrainement;
import com.inkeox.area11.Model.OnUpdateListener;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class LancerEntrainementActivity extends AppCompatActivity implements OnUpdateListener {

    private static final String STATE_COUNTER = "counter";

    // Model
    private Compteur compteur;

    // View
    private ImageView exerciceIcone;
    private TextView exerciceNom;
    private TextView timerValue;
    private Button startPauseButton;

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            compteur = (Compteur) savedInstanceState.getSerializable(STATE_COUNTER);
        }

        setContentView(R.layout.activity_lancer_entrainement);

        if (compteur == null) {
            Entrainement entrainement = (Entrainement) getIntent().getExtras().getSerializable("entrainement");
            compteur = new Compteur(entrainement);
        }

        // Récupérer les views
        exerciceIcone = (ImageView) findViewById(R.id.exerciceIcone);
        exerciceNom = (TextView) findViewById(R.id.exerciceNom);
        timerValue = (TextView) findViewById(R.id.timerValue);
        startPauseButton = (Button) findViewById(R.id.startPauseButton);

        // Abonner l'activité au compteur pour "suivre" les événements
        compteur.addOnUpdateListener(this);

        // Mise à jour graphique
        timerUpdate();
        entrainementInfosUpdate();
    }

    /**
     * Conservation de notre état lors d'un changement
     * @param outState -
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_COUNTER, compteur);
    }

    /**
     * Lancer ou mettre en pause le compteur
     * @param view -
     */
    public void onStartPause(View view) {

        if (startPauseButton.getText().toString().equals(getString(R.string.pause))) {
            compteur.pause();
            startPauseButton.setText(R.string.reprendre);
            return;
        }

        startPauseButton.setText(getString(R.string.pause));
        compteur.start();
    }

    /**
     * Mettre en pause le compteur
     * @param view -
     */
    public void onSkip(View view) {
        compteur.skip();
        startPauseButton.setText(getString(R.string.pause));
    }

    /**
     * Remettre à zéro le compteur
     * @param view -
     */
    public void onReset(View view) {
        compteur.reset();
    }

    /**
     * Mise à jour graphique du compteur
     */
    private void timerUpdate() {
        // Affichage des informations du compteur
        timerValue.setText(String.format("%02d", compteur.getSecondes()) + ":" + String.format("%03d", compteur.getMillisecondes()));
    }

    /**
     * Mise à jour graphique de l'exercice en cours
     */
    private void entrainementInfosUpdate() {
        List<String> infos = compteur.getCurrentGraphicsStep();
        exerciceIcone.setBackgroundColor(Color.DKGRAY); // TODO exerciceIcone
        exerciceNom.setText(infos.get(0));
    }

    /**
     * Méthode appelée à chaque update du compteur (l'activité est abonnée au compteur)
     */
    @Override
    public void onUpdate() {
        timerUpdate();
        entrainementInfosUpdate();
    }
}
