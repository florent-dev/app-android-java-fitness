package com.inkeox.area11.Controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inkeox.area11.Model.Entity.Compteur;
import com.inkeox.area11.Model.Entity.Entrainement;
import com.inkeox.area11.Model.Listener.OnUpdateListener;
import com.inkeox.area11.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class JouerEntrainementActivity extends AppCompatActivity implements OnUpdateListener {

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
        setContentView(R.layout.activity_jouer_entrainement);

        // Instanciation de l'entrainement
        if (savedInstanceState != null) {
            compteur = (Compteur) savedInstanceState.getSerializable(STATE_COUNTER);
        }

        if (compteur == null) {
            Entrainement entrainement = (Entrainement) getIntent().getExtras().getSerializable("entrainement");

            if (entrainement != null) {
                compteur = new Compteur(entrainement, this.getApplicationContext());
            }

        }

        // Récupérer les views
        exerciceIcone = findViewById(R.id.exerciceIcone);
        exerciceNom = findViewById(R.id.exerciceNom);
        timerValue = findViewById(R.id.timerValue);
        startPauseButton = findViewById(R.id.startPauseButton);

        // Abonner l'activité au compteur pour "suivre" les événements
        compteur.addOnUpdateListener(this);

        // Mise à jour graphique
        timerUpdate();
        entrainementInfosUpdate();
    }

    /**
     * Conservation de notre état lors d'un changement
     * @param bundle -
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            bundle.putSerializable(STATE_COUNTER, compteur);
        } catch (Exception $e) {
            // Failure
        }
    }

    /**
     * Application mise en pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        compteur.pause();
        startPauseButton.setText(R.string.reprendre);
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
        } else {
            compteur.jouerSon();
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
        timerValue.setText(String.format("%02d", compteur.getMinutes()) + ":" + String.format("%02d", compteur.getSecondes()));
    }

    /**
     * Mise à jour graphique de l'exercice en cours
     */
    private void entrainementInfosUpdate() {
        List<String> infos = compteur.getCurrentGraphicsStep();
        exerciceNom.setText(infos.get(0));

        try {
            Context context = this.getApplicationContext();
            Drawable d = context.getResources().getDrawable(context.getResources().getIdentifier(infos.get(1), "drawable", context.getPackageName()));
            exerciceIcone.setImageDrawable(d);
            exerciceIcone.setBackgroundColor(Color.TRANSPARENT);
        }
        catch (Exception e) {
            exerciceIcone.setBackgroundColor(Color.DKGRAY);
        }
    }

    /**
     * Méthode appelée à chaque update du compteur (l'activité est abonnée au compteur)
     */
    @Override
    public void onUpdate() {
        timerUpdate();
        entrainementInfosUpdate();
    }

    /**
     Retourne à l'activité hiérarchiquement en amont
     * @param view -
     */
    public void retourActivitePrecedente(View view) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
