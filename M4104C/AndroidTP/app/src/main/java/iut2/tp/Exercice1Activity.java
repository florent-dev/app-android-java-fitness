package iut2.tp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Exercice1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice1);
    }

    public void exercice1Valider(View view) {
        TextView prenom = (TextView) findViewById(R.id.exercice1_prenom);
        TextView hello = (TextView) findViewById(R.id.exercice1_hello);
        hello.setText("Hello " + prenom.getText() + "!");
    }
}
