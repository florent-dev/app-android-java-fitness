package m4104C.exemples.exemple_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import m4104C.exemples.R;

public class Exemple_1_1_6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple_1_1_6);
    }

    // Méthode traitant l'évenement sur le deuxième bouton grâce au tag android:onClick="onClickMonDeuxiemeBouton"
    public void onClickBouton(View view) {

        // Récupération des objets graphiques
        Button monPremierBouton = (Button) findViewById(R.id.monPremierBouton);
        Button monDeuxiemeBouton = (Button) findViewById(R.id.monDeuxiemeBouton);

        //
        String message = null;
        if (view == monPremierBouton) {
            message = "Clique effectué sur le premier bouton";
        } else {
            message = "Clique effectué sur le deuxième bouton";
        }

        //
        Toast toast = Toast.makeText(Exemple_1_1_6Activity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
