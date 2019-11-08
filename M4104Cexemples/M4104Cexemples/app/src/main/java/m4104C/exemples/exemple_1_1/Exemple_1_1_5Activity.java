package m4104C.exemples.exemple_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import m4104C.exemples.R;

public class Exemple_1_1_5Activity extends AppCompatActivity implements View.OnClickListener {

    // VIEW
    Button monPremierBouton;
    Button monDeuxiemeBouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple_1_1_5);

        // Récupération des objets graphiques
        monPremierBouton = (Button) findViewById(R.id.monPremierBouton);
        monDeuxiemeBouton = (Button) findViewById(R.id.monDeuxiemeBouton);

        // Associé l'activité comme listener
        monPremierBouton.setOnClickListener(this);
        monDeuxiemeBouton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //
        String message = null;
        if (view == monPremierBouton) {
            message = "Clique effectué sur le premier bouton";
        } else {
            message = "Clique effectué sur le deuxième bouton";
        }

        //
        Toast toast = Toast.makeText(Exemple_1_1_5Activity.this, message,
                Toast.LENGTH_LONG);
        toast.show();
    }

}
