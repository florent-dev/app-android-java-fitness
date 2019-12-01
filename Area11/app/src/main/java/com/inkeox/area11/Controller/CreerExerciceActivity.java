package com.inkeox.area11.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.inkeox.area11.Model.Entity.Exercice;
import com.inkeox.area11.R;

public class CreerExerciceActivity extends AppCompatActivity {

    // Model
    Exercice exercice = new Exercice();

    // View
    EditText nomExercice;
    EditText tempsExercice;
    EditText tempsReposExercice;

    /**
     * onCreate
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_exercice);

        // On prépare nos vues
        nomExercice = findViewById(R.id.exercice_nom);
        tempsExercice = findViewById(R.id.exercice_temps);
        tempsReposExercice = findViewById(R.id.exercice_temps_repos);

        // On définit les paramètres d'un exercice par défaut
        nomExercice.setText(String.valueOf(exercice.getNom()));
        tempsExercice.setText(String.valueOf(exercice.getTemps()));
        tempsReposExercice.setText(String.valueOf(exercice.getTempsRepos()));
    }

    /**
     * Retourne l'exercice à l'entrainement
     * @param view -
     */
    public void enregistrerExercice(View view) {

        // Mise à jour des informations de l'exercice
        exercice.setNom(String.valueOf(nomExercice.getText()));
        exercice.setTemps(Integer.parseInt(tempsExercice.getText().toString()));
        exercice.setTempsRepos(Integer.parseInt(tempsReposExercice.getText().toString()));

        // On retourne le nouvel exercice sur l'entrainement en création
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercice", exercice);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     Retourne à l'activité hiérarchiquement en amont
     * @param view -
     */
    public void retourActivitePrecedente(View view) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
