package com.inkeox.area11.Model.Listener;

/**
 * Interface intervenant dans le mécanisme d'abonnement auditeur/source
 * En association avec la classe UpdateSource
 *
 */
public interface OnUpdateListener {

    // Méthode appelée à chaque update de l'objet de type UpdateSource (après abonnement)
    void onUpdate();

}

