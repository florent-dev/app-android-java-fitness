package com.inkeox.compteur.main.java.mi1.test_countdowntimer.data;

import java.util.ArrayList;

/**
 * Classe proposant un mécanisme d'abonnement auditeur/source
 * En association avec l'interface OnUpdateListener
 *
 */
public class UpdateSource {

    // Liste des auditeurs
    private ArrayList<OnUpdateListener> listeners = new ArrayList<OnUpdateListener>();

    // Méthode d'abonnement
    public void addOnUpdateListener(OnUpdateListener listener) {
        listeners.add(listener);
    }

    // Méthode activée par la source pour prévenir les auditeurs de l'événement update
    public void update() {

        // Notify everybody that may be interested.
        for (OnUpdateListener listener : listeners)
            listener.onUpdate();
    }
}
