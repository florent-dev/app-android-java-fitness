package m4104C.exemples.exemple_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import m4104C.exemples.R;

public class Exemple_1_1_7Activity extends AppCompatActivity {

    // DATA
    int compteur = 0;

    // VIEW
    TextView compteurText;
    Button boutonAjout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple_1_1_7);

        // Récupération des vues
        compteurText = (TextView) findViewById(R.id.compteur);
        boutonAjout = (Button) findViewById(R.id.boutonAjout);

        // Associer un événement au bouton
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur++;
                miseAJour();
            }
        });

        // Mise à jour
        miseAJour();
    }


    // Mise à jour de l'activité
    private void miseAJour() {

        compteurText.setText(String.valueOf(compteur));
    }
}
