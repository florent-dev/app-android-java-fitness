package com.inkeox.area11.Model.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastNotification {
    public static void afficher(Context applicationContext, String texte) {
        int duree = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(applicationContext, texte, duree);
        toast.show();
    }
}
