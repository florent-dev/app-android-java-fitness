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
    private final static int STEP_TYPE_PAUSE_FIN_SEQ = 3;
    private final static int STEP_TYPE_FINISHED = 4;

    private Entrainement entrainement;
    private CountDownTimer timer;   // https://developer.android.com/reference/android/os/CountDownTimer.html
    private long updatedTime;
    private int currentStepType = STEP_TYPE_PREPARATION;
    private int currentExercice = 0;
    private int currentSequence = 1;

    // Constructeur
    public Compteur(Entrainement entrainement) {
        this.entrainement = entrainement;
        this.updatedTime = entrainement.getPreparationTemps() * 1000;
    }

    // Lancer le compteur
    public void start() {

        Log.d("COMPTEUR", "start()");

        if (timer == null) {

            // Créer le CountDownTimer
            Log.d("LAUNCH_UPDATEDTIME", Long.toString(updatedTime));
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

                    Log.d("GET_UPDATEDTIME", Long.toString(updatedTime));

                    // Si on a pas terminé l'entrainement
                    if (updatedTime > 0) {
                        Compteur.this.timer = null;
                        Compteur.this.start();
                    }

                    // Mise à jour
                    update();
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

    private void setUpdatedTime(long time) {
        this.updatedTime = time;
    }

    // Getters
    public int getMinutes() { return getSecondes() / 60; }
    public int getMillisecondes() { return (int) (updatedTime % 1000); }
    public Entrainement getEntrainement() { return entrainement; }

    public int getSecondes() {
        int secs = (int) (updatedTime / 1000);
        return secs % 60;
    }

    public void skip() {
        if (this.currentStepType < STEP_TYPE_FINISHED) {
            pause();
            skipCurrentStep();
            updatedTime = getCurrentTimeStep();
            timer = null;
            start();
        }
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
                // Ou alors on passe au suivant
                if (this.currentExercice >= this.entrainement.getExercicesCount()) {

                    // On a terminé toutes les séquences
                    // Ou alors on passe à la suivante
                    if (this.currentSequence >= this.entrainement.getSequenceRepetitions()) {
                        this.currentStepType = STEP_TYPE_FINISHED;
                    } else {
                        this.currentExercice = 0;
                        this.currentStepType = STEP_TYPE_PAUSE_FIN_SEQ;
                        this.currentSequence++;
                    }

                } else {
                    this.currentStepType = STEP_TYPE_EXERCICE;
                }
                break;

            case STEP_TYPE_PAUSE_FIN_SEQ:
                this.currentStepType = STEP_TYPE_EXERCICE;
                break;

            case STEP_TYPE_PREPARATION:
                this.currentStepType++;
                break;

        }
        Log.d("SKIP_EXO", Integer.toString(this.currentExercice));
        Log.d("SKIP_STEP", Integer.toString(this.currentStepType));
    }

    /**
     * Récupérer le temps total de l'étape à lancer
     * @return int
     */
    private int getCurrentTimeStep()
    {
        if (this.currentStepType == STEP_TYPE_FINISHED) {
            return 0;
        }

        Exercice exercice = this.entrainement.getExercices().get(this.currentExercice);
        int time = 0;

        switch (this.currentStepType) {
            case STEP_TYPE_EXERCICE:
                time = exercice.getTemps();
                break;
            case STEP_TYPE_PAUSE:
                time = exercice.getTempsRepos();
                break;
            case STEP_TYPE_PAUSE_FIN_SEQ:
                time = entrainement.getSequenceReposTemps();
                break;
        }

        return (time * 1000);
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
            case STEP_TYPE_PAUSE_FIN_SEQ: return Arrays.asList("Longue pause de " + this.entrainement.getSequenceReposTemps() + "sec.", "icone");
            case STEP_TYPE_FINISHED: return Arrays.asList("Terminé !", "icone");
        }

        return Arrays.asList("Erreur", "icone");
    }

}
