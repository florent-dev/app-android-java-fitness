package m4104C.exemples.exemple_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import m4104C.exemples.R;

public class Exemple_1_1_4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple_1_1_4);

        // Récupération de l'objet graphique
        Button monPremierBouton = (Button) findViewById(R.id.monPremierBouton);

        // Associer un événement à cet objet
        monPremierBouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(Exemple_1_1_4Activity.this,
                        "Clique effectué sur le premier bouton", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
