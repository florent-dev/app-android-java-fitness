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

    // Model
    private Compteur compteur;

    // View
    private ImageView exerciceIcone;
    private TextView exerciceNom;
    private TextView timerValue;
    private Button startButton;
    private Button pauseButton;

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer_entrainement);

        Entrainement entrainement = (Entrainement) getIntent().getExtras().getSerializable("entrainement");
        compteur = new Compteur(entrainement);

        // Récupérer les views
        exerciceIcone = (ImageView) findViewById(R.id.exerciceIcone);
        exerciceNom = (TextView) findViewById(R.id.exerciceNom);
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);

        // Abonner l'activité au compteur pour "suivre" les événements
        compteur.addOnUpdateListener(this);

        // Mise à jour graphique
        timerUpdate();
        entrainementInfosUpdate();
    }

    /**
     * Lancer le compteur
     * TODO : non utilisée désormais
     * @param view -
     */
    public void onStart(View view) {
        startButton.setText(getString(R.string.reprendre));
        startButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        compteur.start();
    }

    /**
     * Mettre en pause le compteur
     * @param view -
     */
    public void onPause(View view) {
        startButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        compteur.pause();
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
        timerValue.setText("" + compteur.getMinutes() + ":"
                + String.format("%02d", compteur.getSecondes()) + ":"
                + String.format("%03d", compteur.getMillisecondes()));
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
