package com.inkeox.area11.Model;

import android.os.CountDownTimer;
import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fbm on 24/10/2017.
 */
public class Compteur extends UpdateSource implements Serializable {

    private final static long INITIAL_TIME = 5000;

    private final static int STEP_TYPE_PREPARATION = 0;
    private final static int STEP_TYPE_EXERCICE = 1;
    private final static int STEP_TYPE_PAUSE = 2;
    private final static int STEP_TYPE_FINISHED = 3;

    private Entrainement entrainement;
    private CountDownTimer timer;   // https://developer.android.com/reference/android/os/CountDownTimer.html
    private long updatedTime;
    private int currentStepType = STEP_TYPE_PREPARATION;
    private int currentExercice = 0;

    // Constructeur
    public Compteur(Entrainement entrainement) {
        this.entrainement = entrainement;
        updatedTime = entrainement.getPreparationTemps() * 1000 + 950;
    }

    // Lancer le compteur
    public void start() {

        Log.d("COMPTEUR", "start()");

        if (timer == null) {

            // Créer le CountDownTimer
            timer = new CountDownTimer(updatedTime, 10) {

                // Callback fired on regular interval
                public void onTick(long millisUntilFinished) {
                    updatedTime = millisUntilFinished;

                    // Mise à jour
                    update();
                }

                // Callback fired when the time is up
                public void onFinish() {
                    skipCurrentStep();
                    updatedTime = getCurrentTimeStep();

                    // Mise à jour
                    update();

                    // Si on a pas terminé l'entrainement
                    if (updatedTime > 0) {
                        start();
                    }

                }

            }.start();   // Start the countdown
        }

    }

    // Mettre en pause le compteur
    public void pause() {

        if (timer != null) {
            stop();
            update();
        }

    }

    // Remettre à le compteur à la valeur initiale
    public void reset() {

        if (timer != null) {
            stop();
        }

        updatedTime = INITIAL_TIME;
        update();

    }

    // Arrete l'objet CountDownTimer et l'efface
    private void stop() {
        timer.cancel();
        timer = null;
    }

    // Getters
    public int getMinutes() { return getSecondes() / 60; }
    public int getMillisecondes() { return (int) (updatedTime % 1000); }
    public Entrainement getEntrainement() { return entrainement; }

    public int getSecondes() {
        int secs = (int) (updatedTime / 1000);
        return secs % 60;
    }

    private void skipCurrentStep()
    {
        switch (this.currentStepType) {

            case STEP_TYPE_EXERCICE:
                this.currentStepType = STEP_TYPE_PAUSE;
                break;

            case STEP_TYPE_PAUSE:
                this.currentExercice++;

                // On a terminé tous les exerices
                if (this.currentExercice >= this.entrainement.getExercicesCount()) {
                    this.currentStepType = STEP_TYPE_FINISHED;
                } else {
                    this.currentStepType = STEP_TYPE_EXERCICE;
                }

                break;

            case STEP_TYPE_PREPARATION:
                this.currentStepType++;
                break;

        }
    }

    /**
     * Récupérer le temps total de l'étape à lancer
     * @return int
     */
    private int getCurrentTimeStep()
    {
        Log.d("COMPTEUR", "getCurrentTimeStep");
        if (this.currentStepType == STEP_TYPE_FINISHED) {
            return 0;
        }

        Exercice exercice = this.entrainement.getExercices().get(this.currentExercice);
        return (this.currentStepType == STEP_TYPE_PAUSE) ? (exercice.getTempsRepos() * 1000+900) : (exercice.getTemps() * 1000+900);
    }

    /**
     * Récupérer les informations graphiques de l'étape en cours
     * @return List
     */
    public List<String> getCurrentGraphicsStep()
    {
        switch (this.currentStepType) {
            case STEP_TYPE_PREPARATION: return Arrays.asList("Préparation en cours... :)", "icone");
            case STEP_TYPE_EXERCICE: return Arrays.asList(this.entrainement.getExercices().get(this.currentExercice).getNom(), "icone");
            case STEP_TYPE_PAUSE: return Arrays.asList("Pause de " + this.entrainement.getExercices().get(this.currentExercice).getTempsRepos() + "sec.", "icone");
            case STEP_TYPE_FINISHED: return Arrays.asList("Terminé !", "icone");
        }

        return Arrays.asList("Erreur", "icone");
    }

}
